package com.vinci.devmatch.modules.user.service;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;

public interface UserService {

    UserResponse getUserById(Long id);

    UserResponse getUserByAuth0Id(String auth0Id);

    UserResponse updateUserProfile(Long userId, UserProfileUpdateRequest request);

    UserResponse finishUserProfile(Long userId, UserProfileFinishRequest request);
}