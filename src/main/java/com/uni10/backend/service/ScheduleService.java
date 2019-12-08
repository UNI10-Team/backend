package com.uni10.backend.service;

import com.uni10.backend.api.dto.ScheduleDTO;
import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.api.requests.ScheduleRequest;
import com.uni10.backend.entity.Schedule;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.repository.ScheduleRepository;
import com.uni10.backend.specifications.Specifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private CourseRepository courseRepository;

    public Optional<ScheduleDTO> findById(final Long id) {
        return scheduleRepository.findById(id).map(ScheduleService::scheduleDTO);
    }

    public Page<ScheduleDTO> findAll(final ScheduleRequest scheduleRequest, final long courseId) {
        if (courseRepository.existsById(courseId)) {
            final String[] pathToCourse = {"courseId"};
            final Pageable pageable = scheduleRequest.toPageable();
            final Specification<Schedule> specification =
                    scheduleRequest.toSpecification().and(Specifications.equal(pathToCourse, courseId));

            return scheduleRepository.findAll(specification, pageable).map(ScheduleService::scheduleDTO);
        } else {
            throw new RuntimeException();
        }
    }


    public Optional<ScheduleDTO> save(final ScheduleDTO scheduleDTO, final long courseId) {
        if (courseRepository.existsById(courseId)) {
            Schedule schedule = schedule(scheduleDTO, 0);
            schedule = scheduleRepository.save(schedule);
            return Optional.ofNullable(scheduleDTO(schedule));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ScheduleDTO> update(final ScheduleDTO dto, final long id) {
        if (scheduleRepository.existsById(id)) {
            Schedule schedule = schedule(dto, id);
            schedule = scheduleRepository.save(schedule);
            return Optional.of(scheduleDTO(schedule));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(final Long id) {
        scheduleRepository.deleteById(id);
    }

    private static ScheduleDTO scheduleDTO(final Schedule schedule) {
        return new ScheduleDTO()
                .setId(schedule.getId())
                .setCourseId(schedule.getCourseId())
                .setDay(schedule.getDay())
                .setFromTime(schedule.getFromTime())
                .setToTime(schedule.getToTime())
                .setRoom(schedule.getRoom())
                .setTeacherId(schedule.getTeacherId());
    }

    private static Schedule schedule(final ScheduleDTO scheduleDTO, final long id) {
        return new Schedule()
                .setId(id)
                .setCourseId(scheduleDTO.getCourseId())
                .setDay(scheduleDTO.getDay())
                .setFromTime(scheduleDTO.getFromTime())
                .setToTime(scheduleDTO.getToTime())
                .setRoom(scheduleDTO.getRoom())
                .setTeacherId(scheduleDTO.getTeacherId());
    }
}
