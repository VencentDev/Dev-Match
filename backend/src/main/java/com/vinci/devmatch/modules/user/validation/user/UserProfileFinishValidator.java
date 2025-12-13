package com.vinci.devmatch.modules.user.validation.user;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserProfileFinishValidator implements ConstraintValidator<ValidUserProfileFinish, UserProfileFinishRequest> {

    @Override
    public boolean isValid(UserProfileFinishRequest dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        if (dto.getContactInfo() == null) return false;

        if (dto.getFreelancerProfile() != null) {
            return true;
        }

        return true;
    }
}

