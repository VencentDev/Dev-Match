package com.vinci.devmatch.modules.user.usecase;

import com.vinci.devmatch.modules.user.dto.user.UserResponse;

public interface GetUserByIdUseCase {
    UserResponse execute(Long userId);
}
