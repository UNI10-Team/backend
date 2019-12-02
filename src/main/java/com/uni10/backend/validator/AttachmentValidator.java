package com.uni10.backend.validator;

import com.uni10.backend.annotations.AttachmentValid;
import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class AttachmentValidator implements ConstraintValidator<AttachmentValid, AttachmentDTO> {

    private CourseRepository courseRepository;

    @Override
    public boolean isValid(AttachmentDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getType() == null || value.getType().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Type should not be empty")
                    .addPropertyNode("type")
                    .addConstraintViolation();
        }

        if(value.getData() == null || value.getData().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Data should not be empty")
                    .addPropertyNode("type")
                    .addConstraintViolation();
        }

        if(value.getName() == null || value.getName().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Name should not be empty")
                    .addPropertyNode("name")
                    .addConstraintViolation();
        }

        if(!courseRepository.existsById(value.getCourseId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Course should be real")
                    .addPropertyNode("courseId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

