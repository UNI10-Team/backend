package com.uni10.backend.api.filter;

import com.uni10.backend.specifications.Specifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Filter<T> {

    Specification<T> toSpecification();

    default Specification<T> toSpecification(final String key, final List<String> values) {
        final Set<FilterCriteria<T>> criterias = values.stream()
                .map(value -> toCriteria(key, value))
                .collect(Collectors.toSet());

        Specification<T> equal = null;
        Specification<T> others = null;

        for (FilterCriteria<T> criteria : criterias) {
            if (criteria.getOperator() == Operator.EQUAL || criteria.getOperator() == Operator.DEFAULT) {
                equal = criteria.toSpecification().or(equal);
            }
            else{
                others = criteria.toSpecification().and(others);
            }
        }

        if(others != null){
            if(equal != null) {
                return equal.and(others);
            }
            else{
                return others;
            }
        }
        else {
            return equal;
        }

    }

    default Specification<T> toSpecification(final String key, final String value) {
        if (value == null || "".equals(value)) {
            return Specifications.conjunction();
        }
        return toCriteria(key, value).toSpecification();
    }

    default FilterCriteria<T> toCriteria(final String key, final String value) {
        final String[] args = value.split("=");
        final Operator operator = Operator.fromSimpleFormat((args.length == 2) ? args[0] : "");
        final String realValue = (args.length == 2) ? args[1] : args[0];
        return new FilterCriteria<>(key, operator, realValue);
    }

}
