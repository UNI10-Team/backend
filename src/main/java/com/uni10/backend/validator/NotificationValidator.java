package com.uni10.backend.validator;

import com.uni10.backend.annotations.NotificationValid;
import com.uni10.backend.api.dto.NotificationDTO;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class NotificationValidator implements ConstraintValidator<NotificationValid, NotificationDTO> {

    private UserRepository userRepository;

    @Override
    public boolean isValid(NotificationDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getText() == null || value.getText().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Text should not be empty")
                    .addPropertyNode("text")
                    .addConstraintViolation();
        }

        if(value.getOpened() != true && value.getOpened() != false){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Opened should be true or false")
                    .addPropertyNode("opened")
                    .addConstraintViolation();
        }

        if(!userRepository.existsById(value.getUserId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("User should be real")
                    .addPropertyNode("userId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

