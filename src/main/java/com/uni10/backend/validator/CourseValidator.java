package com.uni10.backend.validator;

import com.uni10.backend.annotations.CourseValid;
import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class CourseValidator implements ConstraintValidator<CourseValid, CourseDTO> {

    private SubjectRepository subjectRepository;

    @Override
    public boolean isValid(CourseDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getType() == null || value.getType().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Type should not be empty")
                    .addPropertyNode("type")
                    .addConstraintViolation();
        }

        if(!subjectRepository.existsById(value.getSubjectId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Subject should be real")
                    .addPropertyNode("subjectId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

