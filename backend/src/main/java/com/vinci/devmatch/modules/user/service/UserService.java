package com.vinci.devmatch.modules.user.service;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserResponse> getAllUsers(
            Role role,
            String industry,
            Boolean profileCompleted,
            Pageable pageable
    );

    UserResponse getUserById(Long id);

    UserResponse getUserByAuth0Id(String auth0Id);

    UserResponse updateUserProfile(Long userId, UserProfileUpdateRequest request);

    UserResponse finishUserProfile(Long userId, UserProfileFinishRequest request);
}