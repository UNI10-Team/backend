package com.uni10.backend.service;

import com.uni10.backend.api.dto.AttendanceDTO;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.AttendanceRequest;
import com.uni10.backend.entity.Attendance;
import com.uni10.backend.repository.AttendanceRepository;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceService {

    private AttendanceRepository attendanceRepository;

    public Page<AttendanceDTO> findAll(final AttendanceRequest attendanceRequest) {
        final Pageable pageable = attendanceRequest.toPageable();
        final Specification<Attendance> specification = attendanceRequest.toSpecification();
        return attendanceRepository.findAll(specification, pageable).map(AttendanceService::attendanceDTO);
    }

    public Optional<AttendanceDTO> findById(final Long id) {
        return attendanceRepository.findById(id).map(AttendanceService::attendanceDTO);
    }

    public AttendanceDTO save(AttendanceDTO attendanceDTO) {
        Attendance course = attendance(attendanceDTO);
        course = attendanceRepository.save(course);
        return attendanceDTO(course);
    }

    public AttendanceDTO update(final AttendanceDTO attendanceDTO, final long id) {
        val optional = attendanceRepository.findById(id);
        Attendance attendance = attendance(optional.orElseThrow(NotFoundException::new), attendanceDTO);
        attendance = attendanceRepository.save(attendance);
        return attendanceDTO(attendance);
    }

    public void deleteById(final long id) {
        if (attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
        }
        else{
            throw new NotFoundException();
        }
    }


    private static AttendanceDTO attendanceDTO(final Attendance attendance) {
        return new AttendanceDTO()
                .setId(attendance.getId())
                .setScheduleId(attendance.getScheduleId())
                .setStudentId(attendance.getStudentId());
    }

    private static Attendance attendance(final AttendanceDTO attendanceDTO) {
        return new Attendance()
                .setId(0)
                .setScheduleId(attendanceDTO.getScheduleId())
                .setStudentId(attendanceDTO.getStudentId());
    }

    private static Attendance attendance(final Attendance attendance, final AttendanceDTO attendanceDTO) {
        return attendance
                .setScheduleId(attendanceDTO.getScheduleId())
                .setStudentId(attendanceDTO.getStudentId());
    }
}

