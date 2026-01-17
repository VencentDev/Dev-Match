package com.vinci.devmatch.modules.user.dto.user;

import com.vinci.devmatch.modules.user.dto.ContactInfo;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private ContactInfo contactInfo;
    private String industry;
    private boolean profileCompleted;

    private String userType;
    private String kycStatus;
    private String role;

    private FreelancerProfileResponse freelancerProfile;
}