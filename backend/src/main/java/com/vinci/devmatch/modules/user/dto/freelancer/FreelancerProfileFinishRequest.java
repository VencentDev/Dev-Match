package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.validation.url.ValidURL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerProfileFinishRequest {

    @NotBlank(message = "Position is required")
    @Size(max = 100)
    private String position;

    @NotEmpty(message = "At least one skill is required")
    @Size(min = 1, max = 30)
    private Set<String> skills;

    private Set<@ValidURL String> links;

    private Set<Education> education;

    private Set<String> certifications;
}