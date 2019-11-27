package com.uni10.backend.api;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel
public class I18NTranslationDTO {

    private String key;
    private String translation;
    private String language;
}
