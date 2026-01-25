package com.vinci.devmatch.modules.auth.service;

import com.vinci.devmatch.modules.auth.dto.LoginResponse;

public interface AuthService {

    /**
     * Handle Auth0 login/signup callback
     * Creates user if first time, returns existing user otherwise
     */
    LoginResponse handleAuth0Callback(String auth0Id, String email, String name, String picture);

    /**
     * Get or create user from Auth0 data
     */
    LoginResponse getOrCreateUser(String auth0Id, String email, String firstName, String lastName);
}