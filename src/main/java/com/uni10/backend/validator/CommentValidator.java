package com.uni10.backend.validator;

import com.uni10.backend.annotations.CommentValid;
import com.uni10.backend.annotations.Validator;
import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.repository.AttachmentRepository;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Validator
@AllArgsConstructor
public class CommentValidator implements ConstraintValidator<CommentValid, CommentDTO> {

    private AttachmentRepository attachmentRepository;
    private UserRepository userRepository;


    @Override
    public boolean isValid(CommentDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getText() == null || value.getText().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Text should not be empty")
                    .addPropertyNode("text")
                    .addConstraintViolation();
        }

        if(!userRepository.existsById(value.getUserId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("User should be real")
                    .addPropertyNode("userId")
                    .addConstraintViolation();
        }

        if(!attachmentRepository.existsById(value.getAttachmentId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Attachment should be real")
                    .addPropertyNode("attachmentId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
