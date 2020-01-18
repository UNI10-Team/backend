package com.uni10.backend.repository;

import com.uni10.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseRepository extends JpaRepositoryImplementation<Course, Long> {

    List<Course> findAllBySubjectIdAndType(long subjectId, String type);

}
