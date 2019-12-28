package com.uni10.backend.repository;

import com.uni10.backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttendanceRepository extends JpaRepositoryImplementation<Attendance, Long> {

}
