package com.vinci.devmatch.modules.user.entity;

import com.vinci.devmatch.modules.user.enums.HighestAttainedEducation;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    private String degree;

    @Enumerated(EnumType.STRING)
    private HighestAttainedEducation highestAttainedEducation;

    private String startYear;
    private String endYear;
}
