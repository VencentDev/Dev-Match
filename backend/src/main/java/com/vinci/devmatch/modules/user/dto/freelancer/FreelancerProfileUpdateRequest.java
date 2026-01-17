package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.validation.education.ValidEducationList;
import com.vinci.devmatch.modules.user.validation.freelancer.ValidFreelancerProfileUpdate;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidFreelancerProfileUpdate
public class FreelancerProfileUpdateRequest {

    private String position;
    private Set<String> skills;
    private Set<String> links;
    private Set<String> languages;
    private Set<String> programmingLanguages; // ← ADDED

    @ValidEducationList
    private Set<Education> education;

    private Set<String> certifications;
}