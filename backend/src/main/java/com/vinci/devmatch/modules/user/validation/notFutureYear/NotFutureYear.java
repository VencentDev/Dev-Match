package com.vinci.devmatch.modules.user.validation.notFutureYear;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotFutureYearValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFutureYear {
    String message() default "Year cannot be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}