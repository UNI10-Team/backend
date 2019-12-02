package com.uni10.backend.annotations;

import com.uni10.backend.validator.AttendanceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AttendanceValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AttendanceValid {

    String message() default "Invalid attendance";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
