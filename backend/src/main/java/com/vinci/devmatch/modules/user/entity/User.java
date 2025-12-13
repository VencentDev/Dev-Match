package com.vinci.devmatch.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinci.devmatch.modules.user.enums.KycStatus;
import com.vinci.devmatch.modules.user.enums.Role;
import com.vinci.devmatch.modules.user.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String fullName;

    @Embedded
    private ContactInfo contactInfo;

    private String governmentIdUrl;

    private String industry;

    private String paymentMethod;

    private boolean emailVerified = false;

    private boolean profileCompleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType = UserType.UNKNOWN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus kycStatus = KycStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = true)
    @JsonIgnore
    private String password;

    private String oauthProvider;
    private String oauthSubjectId;

    // 1:1 freelancer profile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FreelancerProfile freelancerProfile;

    // ------------ Spring Security ---------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = role.name().startsWith("ROLE_")
                ? role.name()
                : "ROLE_" + role.name();

        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override public String getPassword() { return null; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return emailVerified;
    }
}


