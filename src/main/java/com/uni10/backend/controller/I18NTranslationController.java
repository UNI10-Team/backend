package com.uni10.backend.controller;

import com.uni10.backend.api.I18NTranslationDTO;
import com.uni10.backend.entity.I18NTranslation;
import com.uni10.backend.service.I18NTranslationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.uni10.backend.specifications.I18NTranslationSpecifications;


@Api
@RestController
@RequestMapping("/i18ntranslations")
@AllArgsConstructor
public class I18NTranslationController {

    private I18NTranslationService i18NTranslationService;

    @GetMapping
    @ApiOperation("Find all translations")
    public ResponseEntity<List<I18NTranslationDTO>> findAllFiltered(
            @RequestParam(defaultValue = "en", name = "language") String language,
            @RequestParam(required = false, name = "resource") List<String> resources) {
        Specification<I18NTranslation> specification = I18NTranslationSpecifications.findByLanguage(language);
        if(resources != null && !resources.isEmpty()) {
            specification = specification.and(I18NTranslationSpecifications.findByNameIn(resources));
        }
        return ResponseEntity.ok(i18NTranslationService.findAll(specification));
    }
}
