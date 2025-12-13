package com.vinci.devmatch.modules.user.usecase.impl;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.service.UserService;
import com.vinci.devmatch.modules.user.usecase.FinishUserProfileUseCase;
import org.springframework.stereotype.Component;

@Component
public class FinishUserProfileUseCaseImpl implements FinishUserProfileUseCase {

    private final UserService userService;

    public FinishUserProfileUseCaseImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponse execute(Long userId, UserProfileFinishRequest finishRequest) {
        return userService.finishUserProfile(userId, finishRequest);
    }
}
