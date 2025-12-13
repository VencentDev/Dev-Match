package com.vinci.devmatch.modules.user.validation.education;


import com.vinci.devmatch.modules.user.dto.user.Education;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class EducationListValidator implements ConstraintValidator<ValidEducationList, Set<Education>> {

    @Override
    public boolean isValid(Set<Education> value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) return true;

        for (Education e : value) {
            if (e.getDegree() == null || e.getDegree().isBlank()) return false;
            if (e.getHighestAttainedEducation() == null) return false;

            try {
                int start = Integer.parseInt(e.getStartYear());
                int end = Integer.parseInt(e.getEndYear());
                if (start > end) return false;
            } catch (Exception ex) {
                return false;
            }
        }

        return true;
    }
}
