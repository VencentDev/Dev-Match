package com.vinci.devmatch.modules.user.dto.user;


import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileResponse;
import com.vinci.devmatch.modules.user.dto.ContactInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private ContactInfo contactInfo;

    private String industry;

    private boolean emailVerified;

    private boolean profileCompleted;

    private String userType;

    private String kycStatus;

    private String role;

    private FreelancerProfileResponse freelancerProfile;
}
