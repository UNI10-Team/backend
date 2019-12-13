package com.uni10.backend.service;

import com.uni10.backend.api.exceptions.ForbiddenException;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.entity.Course;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ScheduleServiceHelper {

    private final CourseRepository courseRepository;
    private ThreadLocal<Course> course = new ThreadLocal<>();

    @Before("execution(* com.uni10.backend.service.ScheduleService.* (..)) && " +
            "args(.., courseId)")
    public void existsCourse(final long courseId) {
        var optional = courseRepository.findById(courseId);
        if (optional.isPresent()) {
            course.set(optional.get());
        } else {
            throw new NotFoundException("Course not found");
        }
    }


    @Before("execution(* com.uni10.backend.service.ScheduleService.* (..)) && " +
            "@annotation(org.springframework.security.access.annotation.Secured) && " +
            "args(.., courseId)")
    public void isAllowed(final long courseId) {
        if (course.get().getSubject().getTeacherId() != SecurityService.getPrincipal().getId()) {
            throw new ForbiddenException("Only the Subject teacher can perform this action");
        }
    }
}
