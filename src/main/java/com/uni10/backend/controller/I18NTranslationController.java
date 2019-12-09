package com.uni10.backend.controller;

import com.uni10.backend.api.requests.I18NTranslationRequest;
import com.uni10.backend.service.I18NTranslationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Api
@RestController
@RequestMapping("/i18ntranslations")
@AllArgsConstructor
public class I18NTranslationController {

    private I18NTranslationService i18NTranslationService;

    @GetMapping
    @ApiOperation(value = "I18NTranslationController.findAll")
    public ResponseEntity<Map<String, String>> findAll(final I18NTranslationRequest i18NTranslationRequest) {
        return ResponseEntity.ok(i18NTranslationService.findAll(i18NTranslationRequest));
    }
}
