package com.vinci.devmatch.modules.auth.dto;

import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private UserResponse user;
    private String message;
    private boolean needsProfileCompletion;

    public static LoginResponse success(UserResponse user) {
        return LoginResponse.builder()
                .user(user)
                .message("Login successful")
                .needsProfileCompletion(!user.isProfileCompleted())
                .build();
    }

    public static LoginResponse newUser(UserResponse user) {
        return LoginResponse.builder()
                .user(user)
                .message("Account created successfully")
                .needsProfileCompletion(true)
                .build();
    }
}