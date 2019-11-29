package com.uni10.backend.api;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.I18NTranslation;
import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class I18NTranslationRequest implements Filter<I18NTranslation> {

    @ApiParam(name = "resource")
    private List<String> resource = new ArrayList<>();

    @ApiParam(name = "language", defaultValue = "en")
    private String language = "en";

    @Override
    public Specification<I18NTranslation> toSpecification() {
        Set<Specification<I18NTranslation>> specifications = new HashSet<>();
        specifications.add(toSpecification("resource_name", resource));
        specifications.add(toSpecification("language", language));
        return Specifications.and(specifications);
    }
}
