package com.uni10.backend.service;

import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.exceptions.ForbiddenException;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.CourseRequest;
import com.uni10.backend.entity.Course;
import com.uni10.backend.entity.Schedule;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.security.SecurityService;
import com.uni10.backend.specifications.Specifications;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    public List<CourseDTO> findAll(final CourseRequest courseRequest, final long subjectId) {
        final Specification<Course> specification = courseRequest.toSpecification();
        return courseRepository.findAll(bySubjectId(subjectId).and(specification))
                .stream()
                .map(CourseService::courseDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(final long id, long subjectId) {
        val optional = courseRepository.findById(id);
        if (optional.isPresent() && optional.get().getSubjectId() == subjectId) {
            return courseDTO(optional.get());
        } else {
            throw new NotFoundException("Course not found");
        }
    }

    public CourseDTO save(CourseDTO courseDTO, long subjectId) {
        Course course = course(courseDTO, subjectId);
        course = courseRepository.save(course);
        return courseDTO(course);
    }

    public void deleteById(final long id, long subjectId) {
        val optional = courseRepository.findById(id);
        if (optional.isPresent() && optional.get().getSubjectId() == subjectId) {
            Course course = optional.get();
            if (course.getSubject().getTeacherId() == SecurityService.getPrincipal().getId()) {
                courseRepository.delete(course);
            }
            else{
                throw new ForbiddenException("Only the Subject teacher can perform this action");
            }
        } else {
            throw new NotFoundException("Course not found");
        }
    }

    public CourseDTO update(final CourseDTO courseDTO, long id, final long subjectId) {
        val optional = courseRepository.findById(id);
        if (optional.isPresent() && optional.get().getSubjectId() == subjectId) {
            Course course = course(optional.get(), courseDTO);
            course = courseRepository.save(course);
            return courseDTO(course);
        } else {
            throw new NotFoundException("Course not found");
        }
    }

    private static Specification<Course> bySubjectId(final long subjectId) {
        final String[] pathToSubject = {"subject", "id"};
        return Specifications.equal(pathToSubject, subjectId);
    }

    private static CourseDTO courseDTO(final Course course){
        return new CourseDTO()
                .setId(course.getId())
                .setSubjectId(course.getSubjectId())
                .setType(course.getType());
    }

    private static Course course(final CourseDTO courseDTO, final long subjectId){
        return new Course()
                .setId(0)
                .setType(courseDTO.getType())
                .setSubjectId(subjectId);
    }

    private static Course course(final Course course, final CourseDTO courseDTO){
        return course
                .setType(courseDTO.getType())
                .setSubjectId(courseDTO.getSubjectId());
    }
}
