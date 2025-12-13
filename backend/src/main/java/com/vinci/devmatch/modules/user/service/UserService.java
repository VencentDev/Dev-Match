package com.vinci.devmatch.modules.user.service;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;

public interface UserService {

    UserResponse getUserById(Long id);

    UserResponse updateUserProfile(Long userId, UserProfileUpdateRequest updateRequest);

    UserResponse finishUserProfile(Long userId, UserProfileFinishRequest finishRequest);

}
