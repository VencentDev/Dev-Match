package com.vinci.devmatch.modules.auth.service;

import com.vinci.devmatch.modules.auth.dto.LoginResponse;
import com.vinci.devmatch.modules.auth.service.AuthService;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.entity.User;
import com.vinci.devmatch.modules.user.mapper.UserMapper;
import com.vinci.devmatch.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public LoginResponse handleAuth0Callback(String auth0Id, String email, String name, String picture) {
        log.info("Handling Auth0 callback for user: {}", email);

        // Split name into first and last name
        String[] nameParts = splitName(name);
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        return getOrCreateUser(auth0Id, email, firstName, lastName);
    }

    @Override
    public LoginResponse getOrCreateUser(String auth0Id, String email, String firstName, String lastName) {
        return userRepository.findByAuth0Id(auth0Id)
                .map(existingUser -> {
                    log.info("Existing user found: {}", email);
                    UserResponse userResponse = userMapper.toUserResponse(existingUser);
                    return LoginResponse.success(userResponse);
                })
                .orElseGet(() -> {
                    log.info("Creating new user: {}", email);
                    User newUser = createNewUser(auth0Id, email, firstName, lastName);
                    User savedUser = userRepository.save(newUser);
                    UserResponse userResponse = userMapper.toUserResponse(savedUser);
                    return LoginResponse.newUser(userResponse);
                });
    }

    // ========== Private Helper Methods ==========

    private User createNewUser(String auth0Id, String email, String firstName, String lastName) {
        return User.builder()
                .auth0Id(auth0Id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(null)  // Will be set during profile completion
                .profileCompleted(false)
                .build();
    }

    private String[] splitName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return new String[]{"", ""};
        }

        String[] parts = fullName.trim().split("\\s+", 2);
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";

        return new String[]{firstName, lastName};
    }
}