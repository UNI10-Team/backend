package com.uni10.backend.service;

import com.uni10.backend.api.requests.I18NTranslationRequest;
import com.uni10.backend.entity.I18NTranslation;
import com.uni10.backend.repository.I18NTranslationRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class I18NTranslationService {

    private I18NTranslationRepository i18NTranslationRepository;

    public Map<String, String> findAll(final I18NTranslationRequest i18NTranslationRequest) {
        final Specification<I18NTranslation> specification = i18NTranslationRequest.toSpecification();
        return i18NTranslationRepository
                .findAll(specification)
                .stream()
                .collect(Collectors.toMap(
                        i18NTranslation -> i18NTranslation.getResource().getName(),
                        I18NTranslation::getTranslation));
    }

    public String getTranslationMessage(final long resourceId, final String language) {
        val optional = i18NTranslationRepository.findByResourceIdAndLanguage(resourceId, language);
        if (optional.isPresent()) {
            return optional.get().getTranslation();
        } else {
            val enOptional = i18NTranslationRepository.findByResourceIdAndLanguage(resourceId, language);
            return enOptional.map(I18NTranslation::getTranslation).orElse("");
        }
    }
}
