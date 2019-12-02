package com.uni10.backend.annotations;

import com.uni10.backend.validator.CourseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CourseValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseValid {

    String message() default "Invalid course";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

