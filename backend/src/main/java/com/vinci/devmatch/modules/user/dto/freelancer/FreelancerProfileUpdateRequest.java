package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.dto.freelancer.Education;
import com.vinci.devmatch.modules.user.validation.education.ValidEducationList;
import com.vinci.devmatch.modules.user.validation.freelancer.ValidFreelancerProfileUpdate;
import lombok.*;

import java.util.List;
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

    private List<String> links;

    private Set<String> languages;

    @ValidEducationList
    private Set<Education> education;

    private Set<String> certifications;
}

