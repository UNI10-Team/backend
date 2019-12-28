package com.uni10.backend.repository;

import com.uni10.backend.entity.Schedule;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ScheduleRepository extends JpaRepositoryImplementation<Schedule, Long> {

}
