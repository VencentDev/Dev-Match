package com.vinci.devmatch.modules.user.dto.user;

import com.vinci.devmatch.modules.user.dto.ContactInfo;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest;
import com.vinci.devmatch.modules.user.validation.user.ValidUserProfileUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidUserProfileUpdate
public class UserProfileUpdateRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank
    private String fullName;

    @Valid
    private ContactInfo contactInfo;

    private String governmentIdUrl;

    @NotBlank
    private String industry;

    private String paymentMethod;

    @Valid
    private FreelancerProfileUpdateRequest freelancerProfile;
}


