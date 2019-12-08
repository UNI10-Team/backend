package com.uni10.backend.service;

import com.uni10.backend.api.dto.AttendanceDTO;
import com.uni10.backend.api.requests.AttendanceRequest;
import com.uni10.backend.entity.Attendance;
import com.uni10.backend.repository.AttendanceRepository;
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

    public AttendanceDTO save(AttendanceDTO attendanceDTO) {
        Attendance course = attendance(attendanceDTO, 0);
        course = attendanceRepository.save(course);
        return attendanceDTO(course);
    }

    public Optional<AttendanceDTO> findById(final Long id) {
        return attendanceRepository.findById(id).map(AttendanceService::attendanceDTO);
    }

    public Page<AttendanceDTO> findAll(final AttendanceRequest attendanceRequest) {
        final Pageable pageable = attendanceRequest.toPageable();
        final Specification<Attendance> specification = attendanceRequest.toSpecification();
        return attendanceRepository.findAll(specification, pageable).map(AttendanceService::attendanceDTO);
    }

    public Optional<AttendanceDTO> update(final AttendanceDTO attendanceDTO, final long id) {
        if (attendanceRepository.existsById(id)) {
            Attendance attendance = attendance(attendanceDTO, id);
            attendance = attendanceRepository.save(attendance);
            return Optional.of(attendanceDTO(attendance));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(final Long id) {
        attendanceRepository.deleteById(id);
    }


    private static AttendanceDTO attendanceDTO(final Attendance attendance){
        return new AttendanceDTO()
                .setId(attendance.getId())
                .setScheduleId(attendance.getScheduleId())
                .setStudentId(attendance.getStudentId());
    }

    private static Attendance attendance(final AttendanceDTO attendanceDTO, final long id){
        return new Attendance()
                .setId(id)
                .setScheduleId(attendanceDTO.getScheduleId())
                .setStudentId(attendanceDTO.getStudentId());
    }
}

