package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.enums.HighestAttainedEducation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String degree;
    private HighestAttainedEducation highestAttainedEducation;
    private String startYear;
    private String endYear;
}
