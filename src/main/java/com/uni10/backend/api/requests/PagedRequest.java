package com.uni10.backend.api.requests;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagedRequest {
    @ApiParam(name = "page", defaultValue = "0")
    private int page = 0;

    @ApiParam(name = "size", defaultValue = "20")
    private int size = 20;

    @ApiParam(name = "sort", example = "[name$ASC,name$DESC,name]")
    private List<String> sort = new ArrayList<>();

    public Pageable toPageable(){
        Sort sorted = Sort.unsorted();
        for (final String s : sort) {
            final String[] args = s.split("\\$");
            final String field = args[0];
            final String order = (args.length == 2)? args[1] : "ASC";
            sorted = sorted.and(Sort.by(Sort.Direction.fromString(order), field));
        }
        return PageRequest.of(page, size, sorted);
    }
}
