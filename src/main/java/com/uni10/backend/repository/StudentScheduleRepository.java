package com.uni10.backend.repository;

import com.uni10.backend.entity.StudentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentScheduleRepository extends JpaRepository<StudentSchedule, Long>,
        JpaSpecificationExecutor<StudentSchedule> {

}
