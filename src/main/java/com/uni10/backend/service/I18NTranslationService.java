package com.uni10.backend.service;

import com.uni10.backend.api.dto.I18NTranslationDTO;
import com.uni10.backend.api.requests.I18NTranslationRequest;
import com.uni10.backend.entity.I18NTranslation;
import com.uni10.backend.repository.I18NTranslationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class I18NTranslationService {

    private I18NTranslationRepository i18NTranslationRepository;

    public List<I18NTranslationDTO> findAll(final I18NTranslationRequest i18NTranslationRequest) {
        final Specification<I18NTranslation> specification = i18NTranslationRequest.toSpecification();
        return i18NTranslationRepository
                .findAll(specification)
                .stream()
                .map(I18NTranslationService::toI18NTranslationDTO)
                .collect(Collectors.toList());
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
