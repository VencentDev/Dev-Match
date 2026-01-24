package com.vinci.devmatch.modules.user.validation.notFutureYear;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

public class NotFutureYearValidator implements ConstraintValidator<com.vinci.devmatch.modules.user.validation.notFutureYear.NotFutureYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // Null is valid (handled by @NotNull if required)
        if (value == null) {
            return true;
        }

        int currentYear = Year.now().getValue();
        return value <= currentYear;
    }
}