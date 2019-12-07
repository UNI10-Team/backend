package com.uni10.backend.api.requests;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.api.requests.PagedRequest;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class SubjectRequest extends PagedRequest implements Filter<Subject> {

    @ApiParam(name = "name")
    private List<String> name = new ArrayList<>();

    @Override
    public Specification<Subject> toSpecification() {
        Set<Specification<Subject>> specifications = new HashSet<>();
        specifications.add(toSpecification("name", name));
        return Specifications.and(specifications);
    }
}
