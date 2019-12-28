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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public Page<ScheduleDTO> findAll(final ScheduleRequest scheduleRequest) {
        final Pageable pageable = scheduleRequest.toPageable();
        final Specification<Schedule> specification = scheduleRequest.toSpecification();
        return scheduleRepository.findAll(specification, pageable).map(ScheduleService::scheduleDTO);
    }

    public Optional<ScheduleDTO> findById(final long id) {
        return scheduleRepository.findById(id).map(ScheduleService::scheduleDTO);
    }

    public ScheduleDTO save(final ScheduleDTO scheduleDTO) {
        Schedule schedule = schedule(scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        return scheduleDTO(schedule);
    }

    public ScheduleDTO update(final ScheduleDTO scheduleDTO, final long id) {
        val optional = scheduleRepository.findById(id);
        Schedule schedule = schedule(optional.orElseThrow(NotFoundException::new), scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        return scheduleDTO(schedule);
    }

    public void deleteById(final long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }

    private static ScheduleDTO scheduleDTO(final Schedule schedule) {
        return new ScheduleDTO()
                .setId(schedule.getId())
                .setCourseId(schedule.getCourseId())
                .setDay(schedule.getDay())
                .setStartAt(schedule.getStartAt())
                .setEndAt(schedule.getEndAt())
                .setRoom(schedule.getRoom())
                .setTeacherId(schedule.getTeacherId());
    }

    private static Schedule schedule(final ScheduleDTO scheduleDTO) {
        return new Schedule()
                .setId(0)
                .setDay(scheduleDTO.getDay())
                .setStartAt(scheduleDTO.getStartAt())
                .setEndAt(scheduleDTO.getEndAt())
                .setRoom(scheduleDTO.getRoom())
                .setCourseId(scheduleDTO.getCourseId())
                .setTeacherId(scheduleDTO.getTeacherId());
    }

    private static Schedule schedule(final Schedule schedule, final ScheduleDTO scheduleDTO) {
        return schedule
                .setDay(scheduleDTO.getDay())
                .setStartAt(scheduleDTO.getStartAt())
                .setEndAt(scheduleDTO.getEndAt())
                .setRoom(scheduleDTO.getRoom())
                .setTeacherId(scheduleDTO.getTeacherId());
    }
}
