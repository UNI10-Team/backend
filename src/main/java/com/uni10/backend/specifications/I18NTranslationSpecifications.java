package com.uni10.backend.specifications;

import com.uni10.backend.entity.I18NTranslation;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class I18NTranslationSpecifications {

    public static Specification<I18NTranslation> findByLanguage(final String language){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("language"), language);
    }

    public static Specification<I18NTranslation> findByNameIn(final List<String> resources){
        return (root, query, criteriaBuilder) -> root.get("resource").get("name").in(resources);
    }
}
