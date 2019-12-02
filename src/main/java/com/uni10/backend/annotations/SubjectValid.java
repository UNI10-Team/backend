package com.uni10.backend.annotations;

import com.uni10.backend.validator.SubjectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SubjectValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SubjectValid {

    String message() default "Invalid subject";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
