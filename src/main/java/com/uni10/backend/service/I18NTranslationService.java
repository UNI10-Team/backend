package com.uni10.backend.service;

import com.uni10.backend.api.I18NTranslationDTO;
import com.uni10.backend.entity.I18NTranslation;
import com.uni10.backend.repository.I18NTranslationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class I18NTranslationService {

    private I18NTranslationRepository i18NTranslationRepository;

    public List<I18NTranslationDTO> findAll(Specification<I18NTranslation> spec) {
        return i18NTranslationRepository.findAll(spec)
                .stream()
                .map(I18NTranslationService::toI18NTranslationDTO)
                .collect(Collectors.toList());
    }

    public List<I18NTranslation> findAllByLanguage(String language){
        return i18NTranslationRepository.findAll((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("language"), language));
    }

    private static I18NTranslationDTO toI18NTranslationDTO(final I18NTranslation i18NTranslation){
        return I18NTranslationDTO
                .builder()
                .translation(i18NTranslation.getTranslation())
                .language(i18NTranslation.getLanguage())
                .key(i18NTranslation.getResource().getName())
                .build();
    }
}
