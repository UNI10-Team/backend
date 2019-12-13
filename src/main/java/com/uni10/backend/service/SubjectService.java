package com.uni10.backend.service;

import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.api.exceptions.ForbiddenException;
import com.uni10.backend.api.exceptions.NotFoundException;
import com.uni10.backend.api.requests.SubjectRequest;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.repository.SubjectRepository;
import com.uni10.backend.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectDTO findById(final long id) {
        val optional = subjectRepository.findById(id);
        return subjectDTO(optional.orElseThrow(NotFoundException::new));
    }

    public Page<SubjectDTO> findAll(final SubjectRequest subjectRequest) {
        final Pageable pageable = subjectRequest.toPageable();
        final Specification<Subject> specification = subjectRequest.toSpecification();
        return subjectRepository.findAll(specification, pageable).map(SubjectService::subjectDTO);
    }

    public SubjectDTO save(final SubjectDTO subjectDTO) {
        Subject subject = subject(subjectDTO);
        subject = subjectRepository.save(subject);
        return subjectDTO(subject);
    }

    public SubjectDTO update(final SubjectDTO subjectDTO, final long id) {
        val optional = subjectRepository.findById(id);
        Subject subject = subject(optional.orElseThrow(NotFoundException::new), subjectDTO);
        subject = subjectRepository.save(subject);
        return subjectDTO(subject);
    }

    public void deleteById(final long id) {
        val optional = subjectRepository.findById(id);
        final Subject subject = optional.orElseThrow(NotFoundException::new);
        if (subject.getTeacherId() == SecurityService.getPrincipal().getId()) {
            subjectRepository.delete(subject);
        } else {
            throw new ForbiddenException("Only the Subject teacher can perform this action");
        }
    }

    private static SubjectDTO subjectDTO(final Subject subject) {
        return new SubjectDTO()
                .setId(subject.getId())
                .setName(subject.getName())
                .setTeacherId(subject.getTeacherId());
    }

    private static Subject subject(final SubjectDTO subjectDTO) {
        return new Subject()
                .setId(0)
                .setName(subjectDTO.getName())
                .setTeacherId(subjectDTO.getTeacherId());
    }

    private static Subject subject(final Subject subject, final SubjectDTO subjectDTO) {
        return subject
                .setName(subjectDTO.getName())
                .setTeacherId(subjectDTO.getTeacherId());
    }
}

