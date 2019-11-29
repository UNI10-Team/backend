package com.uni10.backend.demo;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.api.PagedRequest;
import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class PersonRequest extends PagedRequest implements Filter<Person> {

    @ApiParam(name = "name")
    private List<String> name = new ArrayList<>();

    @ApiParam(name = "age")
    private List<String> age = new ArrayList<>();

    @ApiParam(name = "motherId")
    private String motherId;

    @ApiParam(name = "fatherId")
    private String fatherId;

    @Override
    public Specification<Person> toSpecification() {
        Set<Specification<Person>> specifications = new HashSet<>();
        specifications.add(toSpecification("name", name));
        specifications.add(toSpecification("age", age));
        specifications.add(toSpecification("motherId", motherId));
        specifications.add(toSpecification("fatherId", fatherId));
        return Specifications.and(specifications);
    }
}
