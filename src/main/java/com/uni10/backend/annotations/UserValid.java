package com.uni10.backend.annotations;

import com.uni10.backend.validator.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserValid {

    String message() default "Invalid user";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
