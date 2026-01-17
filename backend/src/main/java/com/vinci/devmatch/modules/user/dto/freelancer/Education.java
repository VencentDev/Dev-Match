package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.enums.HighestAttainedEducation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Education level is required")
    private HighestAttainedEducation highestAttainedEducation;

    @Min(value = 1950, message = "Start year must be after 1950")
    @Max(value = 2100, message = "Start year must be before 2100")
    private Integer startYear;

    @Min(value = 1950, message = "End year must be after 1950")
    @Max(value = 2100, message = "End year must be before 2100")
    private Integer endYear;

    private String institution;
}