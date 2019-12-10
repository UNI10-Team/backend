package com.uni10.backend.service;

import com.uni10.backend.api.dto.ScheduleDTO;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.ScheduleRequest;
import com.uni10.backend.entity.Schedule;
import com.uni10.backend.repository.ScheduleRepository;
import com.uni10.backend.security.SecurityService;
import com.uni10.backend.specifications.Specifications;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public Page<ScheduleDTO> findAll(final ScheduleRequest scheduleRequest, final long courseId) {
        final Pageable pageable = scheduleRequest.toPageable();
        final Specification<Schedule> specification = byCourseId(courseId).and(scheduleRequest.toSpecification());
        return scheduleRepository.findAll(specification, pageable).map(ScheduleService::scheduleDTO);
    }

    public ScheduleDTO findById(final long id, final long courseId) {
        val optional = scheduleRepository.findById(id);
        if (optional.isPresent() && optional.get().getCourseId() == courseId) {
            return scheduleDTO(optional.get());
        } else {
            throw new NotFoundException("Schedule not found");
        }
    }

    public ScheduleDTO save(final ScheduleDTO scheduleDTO, final long courseId) {
        Schedule schedule = schedule(scheduleDTO, courseId);
        schedule = scheduleRepository.save(schedule);
        return scheduleDTO(schedule);
    }

    public ScheduleDTO update(final ScheduleDTO scheduleDTO, final long id, final long courseId) {
        val optional = scheduleRepository.findById(id);
        if (optional.isPresent() && optional.get().getCourseId() == courseId) {
            Schedule schedule = schedule(optional.get(), scheduleDTO);
            schedule = scheduleRepository.save(schedule);
            return scheduleDTO(schedule);
        } else {
            throw new NotFoundException("Schedule not found");
        }
    }

    @Secured("ROLE_SUBJECT_TEACHER")
    public void deleteById(final long id, final long courseId) {
        val optional = scheduleRepository.findById(id);
        if (optional.isPresent() && optional.get().getCourseId() == courseId) {
            Schedule schedule = optional.get();
            if (schedule.getCourse().getSubject().getTeacherId() == SecurityService.getPrincipal().getId()) {
                scheduleRepository.delete(schedule);
            }
        } else {
            throw new NotFoundException("Schedule not found");
        }
    }


    private static Specification<Schedule> byCourseId(final long courseId) {
        final String[] pathToCourse = {"course", "id"};
        return Specifications.equal(pathToCourse, courseId);
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

    private static Schedule schedule(final ScheduleDTO scheduleDTO, final long courseId) {
        return new Schedule()
                .setId(0)
                .setCourseId(courseId)
                .setDay(scheduleDTO.getDay())
                .setFromTime(scheduleDTO.getFromTime())
                .setToTime(scheduleDTO.getToTime())
                .setRoom(scheduleDTO.getRoom())
                .setTeacherId(scheduleDTO.getTeacherId());
    }

    private static Schedule schedule(final Schedule schedule, final ScheduleDTO scheduleDTO) {
        return schedule
                .setDay(scheduleDTO.getDay())
                .setFromTime(scheduleDTO.getFromTime())
                .setToTime(scheduleDTO.getToTime())
                .setRoom(scheduleDTO.getRoom())
                .setTeacherId(scheduleDTO.getTeacherId());
    }
}
