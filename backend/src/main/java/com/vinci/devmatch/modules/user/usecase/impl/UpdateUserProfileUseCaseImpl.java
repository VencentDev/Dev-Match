package com.vinci.devmatch.modules.user.usecase.impl;

import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.service.UserService;
import com.vinci.devmatch.modules.user.usecase.UpdateUserProfileUseCase;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserProfileUseCaseImpl implements UpdateUserProfileUseCase {

    private final UserService userService;

    public UpdateUserProfileUseCaseImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponse execute(Long userId, UserProfileUpdateRequest updateRequest) {
        return userService.updateUserProfile(userId, updateRequest);
    }
}
