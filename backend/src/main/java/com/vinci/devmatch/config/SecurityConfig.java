package com.vinci.devmatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()  // Public auth endpoints
                        .anyRequest().authenticated()                  // All other endpoints require JWT
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder()))
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Sanitize issuer to avoid accidental typos like trailing dots or whitespace
        String sanitizedIssuer = (issuer == null) ? "" : issuer.trim();
        // remove trailing dots that can break DNS resolution (e.g. "example.com.")
        while (sanitizedIssuer.endsWith(".")) {
            sanitizedIssuer = sanitizedIssuer.substring(0, sanitizedIssuer.length() - 1);
        }
        // Ensure issuer ends with a single trailing slash to match provider metadata if present
        if (!sanitizedIssuer.endsWith("/")) {
            sanitizedIssuer = sanitizedIssuer + "/";
        }

        if (sanitizedIssuer.isEmpty()) {
            throw new IllegalArgumentException("Property 'spring.security.oauth2.resourceserver.jwt.issuer-uri' must be set and non-empty");
        }

        log.info("Using sanitized issuer for JwtDecoder: {}", sanitizedIssuer);

        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(sanitizedIssuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(sanitizedIssuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}