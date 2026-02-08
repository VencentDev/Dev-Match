package com.vinci.devmatch.modules.user.service;

import com.vinci.devmatch.common.exception.ForbiddenException;
import com.vinci.devmatch.common.exception.ResourceNotFoundException;
import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.entity.FreelancerProfile;
import com.vinci.devmatch.modules.user.entity.User;
import com.vinci.devmatch.modules.user.enums.Role;
import com.vinci.devmatch.modules.user.mapper.FreelancerProfileMapper;
import com.vinci.devmatch.modules.user.mapper.UserMapper;
import com.vinci.devmatch.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FreelancerProfileMapper freelancerProfileMapper;

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserByAuth0Id(String auth0Id) {
        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with auth0Id: " + auth0Id));
        return userMapper.toUserResponse(user);
    }

    // ✅ UPDATED: With pagination and filtering
    @Override
    public Page<UserResponse> getAllUsers(
            Role role,
            String industry,
            Boolean profileCompleted,
            Pageable pageable
    ) {
        Page<User> users = userRepository.findByFilters(role, industry, profileCompleted, pageable);
        return users.map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse updateUserProfile(Long userId, UserProfileUpdateRequest updateRequest) {
        User user = findUserById(userId);
        userMapper.updateUserFromUserProfileUpdateRequest(user, updateRequest);

        if (user.getRole() == Role.FREELANCER && updateRequest.getFreelancerProfile() != null) {
            updateOrCreateFreelancerProfile(user, updateRequest.getFreelancerProfile());
        }

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse finishUserProfile(Long userId, UserProfileFinishRequest finishRequest) {
        User user = findUserById(userId);

        if (user.isProfileComplete()) {
            throw new IllegalStateException("Profile already completed");
        }

        userMapper.updateUserFromUserProfileFinishRequest(user, finishRequest);

        if (finishRequest.getRole() == null) {
            throw new IllegalArgumentException("Role selection is required");
        }

        if (finishRequest.getRole() == Role.ADMIN) {
            throw new ForbiddenException("Cannot self-assign admin role");
        }

        user.setRole(finishRequest.getRole());

        if (finishRequest.getRole() == Role.FREELANCER) {
            if (finishRequest.getFreelancerProfile() == null) {
                throw new IllegalArgumentException("Freelancer profile is required when selecting freelancer role");
            }
            createFreelancerProfile(user, finishRequest.getFreelancerProfile());
        }

        user.setProfileCompleted(true);

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    // ========== Private Helper Methods ==========

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    private void createFreelancerProfile(User user, com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest dto) {
        FreelancerProfile profile = freelancerProfileMapper.toEntity(dto);
        profile.setUser(user);
        user.setFreelancerProfile(profile);
    }

    private void updateOrCreateFreelancerProfile(User user, com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest dto) {
        FreelancerProfile profile = user.getFreelancerProfile();

        if (profile == null) {
            profile = new FreelancerProfile();
            profile.setUser(user);
            user.setFreelancerProfile(profile);
        }

        freelancerProfileMapper.updateEntityFromDto(dto, profile);
    }
}