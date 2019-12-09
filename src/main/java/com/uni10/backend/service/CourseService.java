package com.uni10.backend.service;

import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.requests.CourseRequest;
import com.uni10.backend.entity.Course;
import com.uni10.backend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    public CourseDTO save(CourseDTO courseDTO) {
        Course course = course(courseDTO, 0);
        course = courseRepository.save(course);
        return courseDTO(course);
    }

    public Optional<CourseDTO> findById(final long id) {
        return courseRepository.findById(id).map(CourseService::courseDTO);
    }

    public void deleteById(final long id) {
        courseRepository.deleteById(id);
    }

    public Optional<CourseDTO> update(final CourseDTO courseDTO, final long id) {
        if (courseRepository.existsById(id)) {
            Course course = course(courseDTO, id);
            course = courseRepository.save(course);
            return Optional.of(courseDTO(course));
        } else {
            return Optional.empty();
        }
    }

    public List<CourseDTO> findAll(final CourseRequest courseRequest) {
        final Specification<Course> specification = courseRequest.toSpecification();
        return courseRepository.findAll(specification).stream().map(CourseService::courseDTO).collect(Collectors.toList());
    }

    private static CourseDTO courseDTO(final Course course){
        return new CourseDTO()
                .setId(course.getId())
                .setSubjectId(course.getSubjectId())
                .setType(course.getType());
    }

    private static Course course(final CourseDTO courseDTO, final long id){
        return new Course()
                .setId(id)
                .setType(courseDTO.getType())
                .setSubjectId(courseDTO.getSubjectId());
    }
}
