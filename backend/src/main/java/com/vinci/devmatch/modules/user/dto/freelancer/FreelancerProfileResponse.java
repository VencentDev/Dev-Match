package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.validation.education.ValidEducationList;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreelancerProfileResponse {

    private String position;
    private Set<String> skills;
    private Set<String> links;
    private Set<String> languages;
    private Set<String> programmingLanguages;

    @ValidEducationList
    private Set<Education> education;

    private Set<String> certifications;
}