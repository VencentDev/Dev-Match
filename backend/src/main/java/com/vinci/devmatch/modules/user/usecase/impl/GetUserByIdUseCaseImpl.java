package com.vinci.devmatch.modules.user.usecase.impl;

import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.service.UserService;
import com.vinci.devmatch.modules.user.usecase.GetUserByIdUseCase;
import org.springframework.stereotype.Component;

@Component
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserService userService;

    public GetUserByIdUseCaseImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponse execute(Long userId) {
        return userService.getUserById(userId);
    }
}
