package com.uni10.backend.repository;

import com.uni10.backend.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long>,
        JpaSpecificationExecutor<Schedule> {

}
