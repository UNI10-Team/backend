package com.uni10.backend.service;

import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class ScheduleServiceHelper {

    private CourseRepository courseRepository;

    @Before("execution(* com.uni10.backend.service.ScheduleService.* (..)) && args(.., courseId)")
    public void existsCourse(final long courseId){
        if(!courseRepository.existsById(courseId)){
            throw new NotFoundException("Course not found");
        }
    }
}
