package com.vinci.devmatch.modules.user.entity;

import com.vinci.devmatch.modules.user.enums.HighestAttainedEducation;
import jakarta.persistence.Column;
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
public class EducationInfo {

    @Column(name = "degree", length = 200)
    private String degree;

    @Enumerated(EnumType.STRING)
    @Column(name = "highest_attained_education")
    private HighestAttainedEducation highestAttainedEducation;

    @Column(name = "start_year")
    private Integer startYear; // ← Changed from String

    @Column(name = "end_year")
    private Integer endYear;   // ← Changed from String

    // Optional: Add institution name
    @Column(name = "institution", length = 200)
    private String institution;
}