package com.vinci.devmatch.modules.user.validation.user;

import com.vinci.devmatch.modules.user.dto.user.UserProfileUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserProfileUpdateValidator implements ConstraintValidator<ValidUserProfileUpdate, UserProfileUpdateRequest> {

    @Override
    public boolean isValid(UserProfileUpdateRequest dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        // username + email already validated by annotations
        if (dto.getContactInfo() != null) {
            if (dto.getContactInfo().getCountry().isBlank()) return false;
            if (dto.getContactInfo().getPhone().isBlank()) return false;
        }

        return true;
    }
}