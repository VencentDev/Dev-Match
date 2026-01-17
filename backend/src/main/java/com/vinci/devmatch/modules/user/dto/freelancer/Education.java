package com.vinci.devmatch.modules.user.dto.freelancer;

import com.vinci.devmatch.modules.user.enums.HighestAttainedEducation;
import com.vinci.devmatch.modules.user.validation.notFutureYear.NotFutureYear;
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
    @NotFutureYear(message = "Start year cannot be in the future")
    private Integer startYear;

    @Min(value = 1950, message = "End year must be after 1950")
    @NotFutureYear(message = "End year cannot be in the future")
    private Integer endYear;

    private String institution;
}