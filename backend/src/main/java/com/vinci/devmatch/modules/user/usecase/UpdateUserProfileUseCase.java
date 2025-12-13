package com.vinci.devmatch.modules.user.usecase;

import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;

public interface UpdateUserProfileUseCase {
    UserResponse execute(Long userId, UserProfileUpdateRequest updateRequest);
}
