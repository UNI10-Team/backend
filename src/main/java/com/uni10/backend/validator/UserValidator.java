package com.uni10.backend.validator;

import com.uni10.backend.annotations.UserValid;
import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class UserValidator implements ConstraintValidator<UserValid, UserDTO> {

    @Override
    public boolean isValid(CourseDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getUsername() == null || value.getUsername().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Username should not be empty")
                    .addPropertyNode("username")
                    .addConstraintViolation();
        }

        if(value.getUsername() == null || value.getUsername().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Username should not be empty")
                    .addPropertyNode("username")
                    .addConstraintViolation();
        }

        if(value.getEmail() == null || value.getEmail().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Email should not be empty")
                    .addPropertyNode("email")
                    .addConstraintViolation();
        }

        if(value.getPassword() == null || value.getPassword().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password should not be empty")
                    .addPropertyNode("password")
                    .addConstraintViolation();
        }
        if(value.getFirstName() == null || value.getFirstName().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("FirstName should not be empty")
                    .addPropertyNode("firstName")
                    .addConstraintViolation();
        }
        if(value.getLastName() == null || value.getLastName().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("LastName should not be empty")
                    .addPropertyNode("lastName")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

