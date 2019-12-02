package com.uni10.backend.annotations;


import com.uni10.backend.validator.CommentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommentValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommentValid {

    String message() default "Invalid comment";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

