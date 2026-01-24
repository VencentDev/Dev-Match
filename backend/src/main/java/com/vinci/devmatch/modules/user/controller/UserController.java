package com.vinci.devmatch.modules.user.controller;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import com.vinci.devmatch.modules.user.dto.user.UserResponse;
import com.vinci.devmatch.modules.user.enums.Role;
import com.vinci.devmatch.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
    * Get all users
     * GET /api/users
     */
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) Boolean profileCompleted,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        // Parse role enum safely
        Role roleEnum = null;
        if (role != null && !role.isBlank()) {
            try {
                roleEnum = Role.valueOf(role.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid role: " + role + ". Valid values: CLIENT, FREELANCER, ADMIN");
            }
        }

        // Create Sort object
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        // Create Pageable object
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserResponse> users = userService.getAllUsers(roleEnum, industry, profileCompleted, pageable);
        return ResponseEntity.ok(users);
    }

    /**
     * Get user by ID
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Update user profile
     * PUT /api/users/{id}
     * ✅ CHANGED: Removed "/update-profile" - REST convention uses just the resource path
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserProfileUpdateRequest updateRequest
    ) {
        UserResponse response = userService.updateUserProfile(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Finish user profile setup (first-time profile completion)
     * POST /api/users/{id}/finish-profile
     * Note: This is a specific action, so "/finish-profile" is acceptable
     */
    @PostMapping("/{id}/finish-profile")
    public ResponseEntity<UserResponse> finishUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserProfileFinishRequest finishRequest
    ) {
        UserResponse response = userService.finishUserProfile(id, finishRequest);
        return ResponseEntity.ok(response);
    }
}