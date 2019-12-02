package com.uni10.backend.repository;

import com.uni10.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>,
        JpaSpecificationExecutor<Course> {

}
