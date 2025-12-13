package com.vinci.devmatch.modules.user.validation.education;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EducationListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEducationList {
    String message() default "Invalid education entries";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
