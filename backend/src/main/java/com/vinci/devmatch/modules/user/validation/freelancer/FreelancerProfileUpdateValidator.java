package com.vinci.devmatch.modules.user.validation.freelancer;

import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FreelancerProfileUpdateValidator implements ConstraintValidator<ValidFreelancerProfileUpdate, FreelancerProfileUpdateRequest> {

    @Override
    public boolean isValid(FreelancerProfileUpdateRequest dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        if (dto.getPosition() != null && dto.getPosition().isBlank()) return false;
        if (dto.getSkills() != null && dto.getSkills().isEmpty()) return false;

        return true;
    }
}

