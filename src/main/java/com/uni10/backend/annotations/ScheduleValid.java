package com.uni10.backend.annotations;

import com.uni10.backend.validator.ScheduleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ScheduleValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ScheduleValid {

    String message() default "Invalid schedule";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
