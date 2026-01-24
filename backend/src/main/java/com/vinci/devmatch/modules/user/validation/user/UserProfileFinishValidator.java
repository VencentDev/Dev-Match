package com.vinci.devmatch.modules.user.validation.user;

import com.vinci.devmatch.modules.user.dto.user.UserProfileFinishRequest;
import com.vinci.devmatch.modules.user.enums.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserProfileFinishValidator implements ConstraintValidator<ValidUserProfileFinish, UserProfileFinishRequest> {

    @Override
    public boolean isValid(UserProfileFinishRequest dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getRole() == null) {
            return true; // Let @NotNull handle this
        }

        // Don't allow users to self-assign ADMIN role
        if (dto.getRole() == Role.ADMIN) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Cannot self-assign admin role"
                    )
                    .addPropertyNode("role")
                    .addConstraintViolation();
            return false;
        }

        // If FREELANCER role, freelancerProfile must be provided
        if (dto.getRole() == Role.FREELANCER && dto.getFreelancerProfile() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Freelancer profile is required when selecting freelancer role"
                    )
                    .addPropertyNode("freelancerProfile")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}