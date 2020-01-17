package com.uni10.backend.service;

import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.CourseRequest;
import com.uni10.backend.entity.Attachment;
import com.uni10.backend.entity.Course;
import com.uni10.backend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    public Page<CourseDTO> findAll(final CourseRequest courseRequest) {
        final Pageable pageable = courseRequest.toPageable();
        final Specification<Course> specification = courseRequest.toSpecification();
        return courseRepository.findAll(specification, pageable).map(CourseService::courseDTO);
    }

    public Optional<CourseDTO> findById(final long id) {
        return courseRepository.findById(id).map(CourseService::courseDTO);
    }

    public CourseDTO save(CourseDTO courseDTO) {
        Course course = course(courseDTO);
        course = courseRepository.save(course);
        return courseDTO(course);
    }

    public void deleteById(final long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }

    public CourseDTO update(final CourseDTO courseDTO, long id) {
        val course = courseRepository.findById(id);
        final Course updatedCourse = course(course.orElseThrow(NotFoundException::new), courseDTO);
        courseRepository.save(updatedCourse);
        return courseDTO(updatedCourse);

    }

    private static CourseDTO courseDTO(final Course course) {
        Attachment attachment = course.getAttachment();
        long attachmentId = 0;
        if(attachment != null){
            attachmentId = attachment.getId();
        }
        return new CourseDTO()
                .setId(course.getId())
                .setSubjectId(course.getSubjectId())
                .setType(course.getType())
                .setAttachmentId(attachmentId);
    }

    private static Course course(final CourseDTO courseDTO) {
        return new Course()
                .setId(0)
                .setType(courseDTO.getType())
                .setSubjectId(courseDTO.getSubjectId());
    }

    private static Course course(final Course course, final CourseDTO courseDTO) {
        return course
                .setType(courseDTO.getType())
                .setSubjectId(courseDTO.getSubjectId());
    }
}
