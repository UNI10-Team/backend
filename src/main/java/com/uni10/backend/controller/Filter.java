package com.uni10.backend.controller;

import com.uni10.backend.specifications.Specifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class Filter {

    private MultiValueMap<String, String> params;

    public Filter(MultiValueMap<String, String> fields) {
        this.params = fields;
    }

    public Sort extractSort() {
        List<String> sortFields = params.get("sort");
        params.remove("sort");
        if (sortFields == null) {
            return Sort.unsorted();
        } else {
            return sortFields.stream().map(sortField -> {
                final String[] params = sortField.split(",");
                final String field = params[0];
                final String order = (params.length > 1)? params[1] : "ASC";
                return Sort.by(Sort.Direction.fromString(order), field);
            }).reduce(Sort.unsorted(), Sort::and);
        }
    }

    public Pageable extractPage() {
        final String pageParam = params.getFirst("page");
        final String sizeParam = params.getFirst("size");
        params.remove("page");
        params.remove("size");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 0;
        int size = (sizeParam != null) ? Integer.parseInt(sizeParam) : 24;
        return PageRequest.of(page, size, extractSort());
    }

    public <T> Specification<T> extractFilter(){
        List<Specification<T>> specifications = new LinkedList<>();
        for(final String key : params.keySet()){
            for(final String value : params.get(key)){
                specifications.add(extractSpecification(key, value));
            }
        }
        final Specification<T> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        return specifications.stream().reduce(specification, Specification::and);
    }

    private <T> Specification<T> extractSpecification(String fullPathField, String fullParameter){
        final String[] args = fullParameter.split("=");
        final String[] path = fullPathField.split("_");
        final String operator = args[0];
        final String value = args[1];
        switch (operator){
            case "eq":
                return Specifications.equal(path, value);
            case "ne":
                return Specifications.notEqual(path, value);
            case "lt":
                return Specifications.lessThan(path, value);
            case "le":
                return Specifications.lessThanOrEqualTo(path, value);
            case "gt":
                return Specifications.greaterThan(path, value);
            case "ge":
                return Specifications.greaterThanOrEqualTo(path, value);
            case "lk":
                return Specifications.like(path, value);
            default:
                return Specifications.conjunction();
        }
    }

    private <Y, T> Path<Y> navigate(Root<T> root, String[] paths){
        Path<Y> path = root.get(paths[0]);
        for (int i = 1; i < paths.length; i++) {
            path = path.get(paths[i]);
        }
        return path;
    }
}
