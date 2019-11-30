package com.uni10.backend.repository;

import com.uni10.backend.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GradesRepository extends JpaRepository<Grades, Long>,
        JpaSpecificationExecutor<Grades> {

}