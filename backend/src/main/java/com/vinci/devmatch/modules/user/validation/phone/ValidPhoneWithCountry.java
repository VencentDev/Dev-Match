package com.vinci.devmatch.modules.user.validation.phone;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DynamicPhoneValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneWithCountry {
    String message() default "Invalid phone number for the given country";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

