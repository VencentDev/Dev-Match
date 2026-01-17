package com.vinci.devmatch.modules.user.dto.user;

import com.vinci.devmatch.modules.user.dto.ContactInfo;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest;
import com.vinci.devmatch.modules.user.enums.Role;
import com.vinci.devmatch.modules.user.validation.user.ValidUserProfileFinish;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidUserProfileFinish
public class UserProfileFinishRequest {

    @NotNull(message = "Role selection is required")
    private Role role; // ← ADDED - USER MUST SELECT

    @NotBlank(message = "First name is required")
    private String firstName; // ← ADDED

    @NotBlank(message = "Last name is required")
    private String lastName; // ← ADDED

    @Valid
    private ContactInfo contactInfo;

    private String governmentIdUrl;

    @NotBlank(message = "Industry is required")
    private String industry;

    private String paymentMethod;

    @Valid
    private FreelancerProfileFinishRequest freelancerProfile;
}