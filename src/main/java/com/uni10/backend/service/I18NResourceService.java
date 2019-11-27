package com.uni10.backend.service;

import com.uni10.backend.entity.I18NResource;
import com.uni10.backend.repository.I18NResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class I18NResourceService {

    private I18NResourceRepository i18NResourceRepository;

    public List<I18NResource> findAll() {
        return i18NResourceRepository.findAll();
    }

    public Page<I18NResource> findAll(Pageable pageable) {
        return i18NResourceRepository.findAll(pageable);
    }

    public List<I18NResource> findAll(Specification<I18NResource> spec) {
        return i18NResourceRepository.findAll(spec);
    }
}
