package com.uni10.backend.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class Specifications {

    public static <T, Y> Specification<T> equal(final String[] fullPath, final Y value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(navigate(root, fullPath), value);
    }

    public static <T, Y> Specification<T> notEqual(final String[] fullPath, final Y value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(navigate(root, fullPath), value);
    }

    public static <T> Specification<T> lessThan(final String[] fullPath, final String value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(navigate(root, fullPath), value);
    }

    public static <T> Specification<T> lessThanOrEqualTo(final String[] fullPath, final String value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(navigate(root, fullPath), value);
    }

    public static <T> Specification<T> greaterThan(final String[] fullPath, final String value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(navigate(root, fullPath), value);
    }

    public static <T> Specification<T> greaterThanOrEqualTo(final String[] fullPath, final String value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(navigate(root, fullPath), value);
    }

    public static <T> Specification<T> like(final String[] fullPath, final String value){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(navigate(root, fullPath), "%" + value + "%");
    }

    public static <T> Specification<T> conjunction(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static <T> Specification<T> in(final String[] fullPath, final List<String> value){
        return (root, query, criteriaBuilder) -> navigate(root, fullPath).in(value);
    }

    public static <T> Specification<T> contains(final String[] fullPath, final String value){
        return (root, query, criteriaBuilder) -> null;
    }

    private static  <Y, T> Path<Y> navigate(Root<T> root, String[] paths){
        Path<Y> path = root.get(paths[0]);
        for (int i = 1; i < paths.length; i++) {
            path = path.get(paths[i]);
        }
        return path;
    }
}
