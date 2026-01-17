package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.validation.freelancer.ValidFreelancerProfileFinish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidFreelancerProfileFinish
public class FreelancerProfileFinishRequest {

    @NotBlank(message = "Position is required")
    private String position;

    @NotEmpty(message = "At least one skill is required")
    private Set<String> skills;

    private Set<String> links;

    private Set<String> languages;

    private Set<String> programmingLanguages; // ← ADDED

    private Set<Education> education;

    private Set<String> certifications;
}