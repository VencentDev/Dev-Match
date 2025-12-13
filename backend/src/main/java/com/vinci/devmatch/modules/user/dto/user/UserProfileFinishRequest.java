package com.vinci.devmatch.modules.user.dto.user;


import com.vinci.devmatch.modules.user.dto.ContactInfo;
import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest;
import com.vinci.devmatch.modules.user.validation.user.ValidUserProfileFinish;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Valid
    private ContactInfo contactInfo;

    private String governmentIdUrl;

    @NotBlank
    private String industry;

    private String paymentMethod;

    @Valid
    private FreelancerProfileFinishRequest freelancerProfile;
}


