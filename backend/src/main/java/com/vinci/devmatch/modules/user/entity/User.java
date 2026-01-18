package com.vinci.devmatch.modules.user.entity;

import com.vinci.devmatch.modules.user.enums.KycStatus;
import com.vinci.devmatch.modules.user.enums.Role;
import com.vinci.devmatch.modules.user.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_auth0_id", columnList = "auth0_id"),
        @Index(name = "idx_email", columnList = "email")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "auth0_id")
    private String auth0Id;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Embedded
    private ContactInfo contactInfo;

    @Column(name = "government_id_url")
    private String governmentIdUrl;

    private String industry;
    private String paymentMethod;

    @Builder.Default
    private boolean profileCompleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private UserType userType = UserType.UNKNOWN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private KycStatus kycStatus = KycStatus.PENDING;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private FreelancerProfile freelancerProfile;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;


    public boolean isProfileComplete() {
        return profileCompleted && role != null;
    }

    public boolean needsRoleSelection() {
        return role == null;
    }

    public boolean hasRole(Role requiredRole) {
        return this.role != null && this.role == requiredRole;
    }

    public boolean isAdmin() {
        return hasRole(Role.ADMIN);
    }

    public boolean isFreelancer() {
        return hasRole(Role.FREELANCER);
    }

    public boolean isClient() {
        return hasRole(Role.CLIENT);
    }

    public void setFreelancerProfile(FreelancerProfile freelancerProfile) {
        this.freelancerProfile = freelancerProfile;
        if (freelancerProfile != null) {
            freelancerProfile.setUser(this);
        }
    }
}