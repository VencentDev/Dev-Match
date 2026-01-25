package com.vinci.devmatch.modules.auth.controller;

import com.vinci.devmatch.modules.auth.dto.LoginResponse;
import com.vinci.devmatch.modules.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    /**
     * Handle Auth0 callback after user logs in
     * This is called by your frontend after Auth0 redirects
     */
    @PostMapping("/callback")
    public ResponseEntity<LoginResponse> handleCallback(@AuthenticationPrincipal Jwt jwt) {
        log.info("Auth callback received");

        // Extract user info from JWT
        String auth0Id = jwt.getSubject();  // e.g., "auth0|123" or "google-oauth2|456"
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");
        String picture = jwt.getClaim("picture");

        log.info("Processing login for: {} ({})", name, email);

        LoginResponse response = authService.handleAuth0Callback(auth0Id, email, name, picture);

        return ResponseEntity.ok(response);
    }

    /**
     * Get current authenticated user status
     */
    @GetMapping("/me")
    public ResponseEntity<AuthStatus> getAuthStatus(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            return ResponseEntity.ok(new AuthStatus(false, null, null));
        }

        String auth0Id = jwt.getSubject();
        String email = jwt.getClaim("email");

        return ResponseEntity.ok(new AuthStatus(true, auth0Id, email));
    }

    // Simple DTO for auth status
    public record AuthStatus(boolean authenticated, String auth0Id, String email) {}
}