package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.dto.freelancer.Education;
import com.vinci.devmatch.modules.user.validation.freelancer.ValidFreelancerProfileFinish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidFreelancerProfileFinish
public class FreelancerProfileFinishRequest {

    private String position;

    private Set<String> skills;

    private List<String> links;

    private Set<String> languages;

    private Set<Education> education;

    private Set<String> certifications;
}

