package com.uni10.backend.api.requests;
import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.Course;
import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class CourseRequest extends PagedRequest implements Filter<Course> {

    @ApiParam(name = "type")
    private String type;

    @ApiParam(name = "subject")
    private List<String> subject = new ArrayList<>();

    @Override
    public Specification<Course> toSpecification() {
        Set<Specification<Course>> specifications = new HashSet<>();
        specifications.add(toSpecification("type", type));
        specifications.add(toSpecification("subject", subject));
        return Specifications.and(specifications);
    }
}
