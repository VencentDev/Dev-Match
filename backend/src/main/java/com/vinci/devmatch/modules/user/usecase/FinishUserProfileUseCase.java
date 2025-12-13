package com.vinci.devmatch.modules.user.usecase;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;

public interface FinishUserProfileUseCase {
    UserResponse execute(Long userId, UserProfileFinishRequest finishRequest);
}
