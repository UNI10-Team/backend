package com.uni10.backend.specifications;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class Specifications {

    public static <T> Specification<T> and(Collection<Specification<T>> specifications) {
        Specification<T> result = null;
        for (Specification<T> specification : specifications) {
            if (specification != null) {
                result = specification.and(result);
            }
        }
        return result;
    }

    public static <T, Y> Specification<T> equal(final String[] fullPath, final Y value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(navigate(root, fullPath), value);
    }

    public static <T, Y> Specification<T> notEqual(final String[] fullPath, final Y value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(navigate(root, fullPath), value);
    }

    public static <T, S extends Comparable<S>> Specification<T> lessThan(final String[] fullPath, final S value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(navigate(root, fullPath), value);
    }

    public static <T, S extends Comparable<S>> Specification<T> lessThanOrEqualTo(final String[] fullPath, final S value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(navigate(root, fullPath), value);
    }

    public static <T, S extends Comparable<S>> Specification<T> greaterThan(final String[] fullPath, final S value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(navigate(root, fullPath), value);
    }

    public static <T, S extends Comparable<S>> Specification<T> greaterThanOrEqualTo(final String[] fullPath, final S value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(navigate(root, fullPath), value);
    }

    public static <T> Specification<T> like(final String[] fullPath, final String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(navigate(root, fullPath), "%" + value + "%");
    }

    public static <T> Specification<T> in(final String[] fullPath, final List<String> values) {
        return (root, query, criteriaBuilder) -> navigate(root, fullPath).in(values);
    }

    public static <T> Specification<T> conjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static <T> Specification<T> disjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();
    }

    private static <Y, T> Path<Y> navigate(Root<T> root, String[] paths) {
        Path<Y> path = root.get(paths[0]);
        for (int i = 1; i < paths.length; i++) {
            path = path.get(paths[i]);
        }
        return path;
    }
}
