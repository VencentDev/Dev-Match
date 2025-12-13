package com.vinci.devmatch.modules.user.controller;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.usecase.FinishUserProfileUseCase;
import com.vinci.devmatch.modules.user.usecase.GetUserByIdUseCase;
import com.vinci.devmatch.modules.user.usecase.UpdateUserProfileUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;
    private final FinishUserProfileUseCase finishUserProfileUseCase;

    public UserController(GetUserByIdUseCase getUserByIdUseCase,
                          UpdateUserProfileUseCase updateUserProfileUseCase,
                          FinishUserProfileUseCase finishUserProfileUseCase) {
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.updateUserProfileUseCase = updateUserProfileUseCase;
        this.finishUserProfileUseCase = finishUserProfileUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-profile/{id}")
    public ResponseEntity<UserResponse> updateUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserProfileUpdateRequest updateRequest) {
        UserResponse response = updateUserProfileUseCase.execute(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/finish-profile/{id}")
    public ResponseEntity<UserResponse> finishUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserProfileFinishRequest finishRequest) {
        UserResponse response = finishUserProfileUseCase.execute(id, finishRequest);
        return ResponseEntity.ok(response);
    }
}
