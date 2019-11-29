package com.uni10.backend.demo;

import com.uni10.backend.annotations.Validator;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Validator
@AllArgsConstructor
public class PersonValidator implements ConstraintValidator<ValidPerson, PersonDTO> {

    private PersonRepository personRepository;

    @Override
    public boolean isValid(PersonDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getName() == null || value.getName().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Name should not be empty")
                    .addPropertyNode("name")
                    .addConstraintViolation();
        }

        if(value.getAge() < 0){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Age should not be negative")
                    .addPropertyNode("age")
                    .addConstraintViolation();
        }

        if(!personRepository.existsById(value.getMotherId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Mother should be real")
                    .addPropertyNode("motherId")
                    .addConstraintViolation();
        }

        if(!personRepository.existsById(value.getFatherId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Father should be real")
                    .addPropertyNode("fatherId")
                    .addConstraintViolation();
        }

        return isValid;
    }
            }
