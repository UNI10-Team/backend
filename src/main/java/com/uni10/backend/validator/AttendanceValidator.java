package com.uni10.backend.validator;

import com.uni10.backend.annotations.AttendanceValid;
import com.uni10.backend.api.dto.AttendanceDTO;
import com.uni10.backend.repository.ScheduleRepository;
import com.uni10.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.uni10.backend.annotations.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Validator
@AllArgsConstructor
public class AttendanceValidator implements ConstraintValidator<AttendanceValid, AttendanceDTO> {

    private UserRepository userRepository;
    private ScheduleRepository scheduleRepository;

    @Override
    public boolean isValid(AttendanceDTO value, ConstraintValidatorContext context) {

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if(!userRepository.existsById(value.getStudentId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Student should be real")
                    .addPropertyNode("studentId")
                    .addConstraintViolation();
        }

        if(!scheduleRepository.existsById(value.getScheduleId())){
            isValid = false;
            context.buildConstraintViolationWithTemplate("Schedule should be real")
                    .addPropertyNode("scheduleId")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

