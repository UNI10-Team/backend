package com.uni10.backend.service;

import com.uni10.backend.api.exceptions.ForbiddenException;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.repository.SubjectRepository;
import com.uni10.backend.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CourseServiceHelper {

    private final SubjectRepository subjectRepository;
    private ThreadLocal<Subject> subject = new ThreadLocal<>();

    @Before("execution(* com.uni10.backend.service.CourseService.* (..)) && " +
            "args(.., subjectId)")
    public void existsCourse(final long subjectId) {
        var optional = subjectRepository.findById(subjectId);
        if (optional.isPresent()) {
            subject.set(optional.get());
        } else {
            throw new NotFoundException("Subject not found");
        }
    }


    @Before("execution(* com.uni10.backend.service.ScheduleService.* (..)) && " +
            "@annotation(org.springframework.security.access.annotation.Secured) && " +
            "args(.., subjectId)")
    public void isAllowed(final long subjectId) {
        if (subject.get().getTeacherId() != SecurityService.getPrincipal().getId()) {
            throw new ForbiddenException("Only the Subject teacher can perform this action");
        }
    }
}
