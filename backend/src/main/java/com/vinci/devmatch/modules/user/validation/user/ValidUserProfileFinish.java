package com.vinci.devmatch.modules.user.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserProfileFinishValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserProfileFinish {
    String message() default "Invalid user profile completion details";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

