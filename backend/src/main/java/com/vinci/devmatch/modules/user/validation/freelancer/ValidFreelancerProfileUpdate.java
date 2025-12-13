package com.vinci.devmatch.modules.user.validation.freelancer;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FreelancerProfileUpdateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFreelancerProfileUpdate {
    String message() default "Invalid freelancer profile update data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
