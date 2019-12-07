package com.uni10.backend.service;

import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.api.requests.SubjectRequest;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository subjectRepository;

    public Optional<SubjectDTO> findById(final Long id) {
        return subjectRepository.findById(id).map(SubjectService::subjectDTO);
    }

    public Page<SubjectDTO> findAll(final SubjectRequest subjectRequest) {
        final Pageable pageable = subjectRequest.toPageable();
        final Specification<Subject> specification = subjectRequest.toSpecification();
        return subjectRepository.findAll(specification, pageable).map(SubjectService::subjectDTO);
    }

    public SubjectDTO save(final SubjectDTO dto) {
        Subject subject = subject(dto, 0);
        subject = subjectRepository.save(subject);
        return subjectDTO(subject);
    }

    public Optional<SubjectDTO> update(final SubjectDTO dto, final long id) {
        if (subjectRepository.existsById(id)) {
            Subject subject = subject(dto, id);
            subject = subjectRepository.save(subject);
            return Optional.of(subjectDTO(subject));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(final Long id) {
        subjectRepository.deleteById(id);
    }

    private static SubjectDTO subjectDTO(final Subject subject) {
        return new SubjectDTO()
                .setId(subject.getId())
                .setName(subject.getName())
                .setTeacherId(subject.getTeacherId());
    }

    private static Subject subject(final SubjectDTO subjectDTO, final long id) {
        return new Subject()
                .setId(id)
                .setName(subjectDTO.getName())
                .setTeacherId(subjectDTO.getTeacherId());
    }
}

