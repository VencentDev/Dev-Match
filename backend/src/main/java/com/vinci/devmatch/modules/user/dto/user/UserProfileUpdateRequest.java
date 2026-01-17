package com.vinci.devmatch.modules.user.dto.user;

import com.vinci.devmatch.modules.user.dto.ContactInfo;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest;
import com.vinci.devmatch.modules.user.validation.user.ValidUserProfileFinish;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ValidUserProfileFinish
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdateRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Valid
    private ContactInfo contactInfo;

    private String governmentIdUrl;

    private String industry;

    private String paymentMethod;

    @Valid
    private FreelancerProfileUpdateRequest freelancerProfile;
}