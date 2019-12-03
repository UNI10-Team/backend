package com.uni10.backend.validator;

import com.uni10.backend.annotations.ScheduleValid;
import com.uni10.backend.api.dto.ScheduleDTO;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class ScheduleValidator implements ConstraintValidator<ScheduleValid, ScheduleDTO> {

    private UserRepository userRepository;
    private CourseRepository courseRepository;

    @Override
    public boolean isValid(ScheduleDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(value.getDay() == null || value.getDay().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Day should not be empty")
                    .addPropertyNode("day")
                    .addConstraintViolation();
        }
        if(value.getFromTime() == null){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Fromtime should not be empty")
                    .addPropertyNode("fromTime")
                    .addConstraintViolation();
        }
        if(value.getToTime() == null){
            isValid = false;
            context.buildConstraintViolationWithTemplate("ToTime should not be empty")
                    .addPropertyNode("toTime")
                    .addConstraintViolation();
        }
        if(value.getRoom() == null || value.getRoom().equals("")){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Room should not be empty")
                    .addPropertyNode("day")
                    .addConstraintViolation();
        }

        if(!userRepository.existsById(value.getTeacherId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Teacher should be real")
                    .addPropertyNode("teacherId")
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

