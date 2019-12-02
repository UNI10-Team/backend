package com.uni10.backend.annotations;

import com.uni10.backend.validator.AttachmentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AttachmentValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AttachmentValid {

    String message() default "Invalid attachment";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
