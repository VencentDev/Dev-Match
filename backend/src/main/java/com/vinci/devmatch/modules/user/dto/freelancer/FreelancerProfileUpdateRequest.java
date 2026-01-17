package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.validation.url.ValidURL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreelancerProfileUpdateRequest {

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position must be less than 100 characters")
    private String position;

    @NotEmpty(message = "At least one skill is required")
    @Size(min = 1, max = 30, message = "You can have up to 30 skills")
    private Set<String> skills;

    private Set<@ValidURL String> links;

    private Set<Education> education;

    private Set<String> certifications;
}