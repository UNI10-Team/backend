package com.uni10.backend.service;

import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.api.dto.UserDTO;
import com.uni10.backend.entity.Enroll;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.entity.User;
import com.uni10.backend.repository.EnrollRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollService {

    private EnrollRepository enrollRepository;

    public List<SubjectDTO> findAll(final long studentId) {
        return enrollRepository.findAllByStudentId(studentId)
                .stream()
                .map(EnrollService::subjectDTO)
                .collect(Collectors.toList());
    }

    /**
     * returns list of students enrolled at a subject
     */
    public List<User> getAllStudent(final long subjectId){
        List<Enroll> allEnrolls= enrollRepository.findAllBySubjectId(subjectId);
        List<User> allStudents = new ArrayList<>();
        for(Enroll e:allEnrolls){
            allStudents.add(e.getStudent());
        }
        return allStudents;
    }
    private static SubjectDTO subjectDTO(final Enroll enroll) {
        return new SubjectDTO()
                .setId(enroll.getSubject().getId())
                .setName(enroll.getSubject().getName())
                .setTeacherId(enroll.getSubject().getTeacherId())
                .setYear(enroll.getSubject().getYear());
    }

}
