package com.uni10.backend.api.filter;

import com.uni10.backend.specifications.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
public class FilterCriteria<T, S extends Comparable<S>> {

    private String field;
    private Operator operator;
    private S value;

    public Specification<T> toSpecification(){
        final String[] fullPath = field.split("_");
        switch (operator){
            case EQUAL:
            case DEFAULT:
                return Specifications.equal(fullPath, value);
            case NOT_EQUAL:
                return Specifications.notEqual(fullPath, value);
            case LESS_THAN:
                return Specifications.lessThan(fullPath, value);
            case LESS_OR_EQUAL:
                return Specifications.lessThanOrEqualTo(fullPath, value);
            case GREATER_THAN:
                return Specifications.greaterThan(fullPath, value);
            case GREATER_OR_EQUAL:
                return Specifications.greaterThanOrEqualTo(fullPath, value);
            case LIKE:
                return Specifications.like(fullPath, value.toString());
            default:
                return Specifications.conjunction();

        }
    }
}
