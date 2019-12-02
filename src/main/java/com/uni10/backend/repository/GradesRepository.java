package com.uni10.backend.repository;

import com.uni10.backend.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GradesRepository extends JpaRepository<Grade, Long>,
        JpaSpecificationExecutor<Grade> {

}
