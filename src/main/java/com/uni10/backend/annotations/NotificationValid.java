package com.uni10.backend.annotations;

import com.uni10.backend.validator.NotificationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotificationValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotificationValid {

    String message() default "Invalid notification";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
