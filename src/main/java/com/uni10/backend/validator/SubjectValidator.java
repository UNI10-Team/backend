package com.uni10.backend.validator;

import com.uni10.backend.annotations.SubjectValid;
import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class SubjectValidator implements ConstraintValidator<SubjectValid, SubjectDTO> {

    private UserRepository userRepository;

    @Override
    public boolean isValid(SubjectDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getName() == null || value.getname().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Name should not be empty")
                    .addPropertyNode("name")
                    .addConstraintViolation();
        }

        if(!userRepository.existsById(value.getTeacherId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Teacher should be real")
                    .addPropertyNode("teacherId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

