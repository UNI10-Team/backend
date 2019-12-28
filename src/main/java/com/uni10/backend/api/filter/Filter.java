package com.uni10.backend.api.filter;

import com.uni10.backend.specifications.Specifications;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Filter<T> {

    Specification<T> toSpecification();

    default Specification<T> toSpecification(final String key, final List<String> values) {
        final Set<FilterCriteria<T, String>> criterias = values.stream()
                .map(value -> toCriteria(key, value))
                .collect(Collectors.toSet());

        Specification<T> equal = null;
        Specification<T> others = null;

        for (FilterCriteria<T, String> criteria : criterias) {
            if (criteria.getOperator() == Operator.EQUAL || criteria.getOperator() == Operator.DEFAULT) {
                equal = criteria.toSpecification().or(equal);
            } else {
                others = criteria.toSpecification().and(others);
            }
        }

        if (others != null) {
            if (equal != null) {
                return equal.and(others);
            } else {
                return others;
            }
        } else {
            return equal;
        }
    }

    default Specification<T> toSpecification(final String key, final String value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        return toCriteria(key, value).toSpecification();
    }

    default Specification<T> toTimeSpecification(final String key, final String value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        return toCriteria(key, value, true).toSpecification();
    }

    default FilterCriteria<T, Date> toCriteria(final String key, final String value, boolean time) {
        final String[] args = value.split("=");
        final Operator operator = Operator.fromSimpleFormat((args.length == 2) ? args[0] : "");
        final String realValue = (args.length == 2) ? args[1] : args[0];
        final Date date = Time.valueOf(realValue);
        return new FilterCriteria<>(key, operator, date);
    }

    default FilterCriteria<T, String> toCriteria(final String key, final String value) {
        final String[] args = value.split("=");
        final Operator operator = Operator.fromSimpleFormat((args.length == 2) ? args[0] : "");
        final String realValue = (args.length == 2) ? args[1] : args[0];
        return new FilterCriteria<>(key, operator, realValue);
    }

}
