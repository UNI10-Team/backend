package com.uni10.backend.repository;

import com.uni10.backend.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Enroll.EnrollId> {

    List<Enroll> findAllByStudentId(long student_id);
    List<Enroll> findAllBySubjectId(long subject_id);
}
