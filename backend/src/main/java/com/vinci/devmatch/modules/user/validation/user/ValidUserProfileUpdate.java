package com.vinci.devmatch.modules.user.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserProfileUpdateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserProfileUpdate {
    String message() default "Invalid user profile update data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

