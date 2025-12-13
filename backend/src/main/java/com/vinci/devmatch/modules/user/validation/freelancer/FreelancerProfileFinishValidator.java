package com.vinci.devmatch.modules.user.validation.freelancer;

import com.vinci.devmatch.modules.user.dto.freelancer.FreelancerProfileFinishRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FreelancerProfileFinishValidator implements ConstraintValidator<ValidFreelancerProfileFinish, FreelancerProfileFinishRequest> {

    @Override
    public boolean isValid(FreelancerProfileFinishRequest dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        if (dto.getPosition() == null || dto.getPosition().isBlank()) return false;
        if (dto.getSkills() == null || dto.getSkills().isEmpty()) return false;
        if (dto.getLanguages() == null || dto.getLanguages().isEmpty()) return false;

        return true;
    }
}
