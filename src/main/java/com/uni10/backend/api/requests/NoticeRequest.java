package com.uni10.backend.api.requests;

import com.uni10.backend.entity.Notice;
import io.swagger.annotations.ApiParam;
import lombok.Setter;
import com.uni10.backend.api.filter.Filter;
import org.springframework.data.jpa.domain.Specification;
import com.uni10.backend.specifications.Specifications;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoticeRequest extends PagedRequest implements Filter<Notice> {

    @ApiParam(name = "subjectId")
    private List<String> subjectId = new ArrayList<>();


    @Override
    public Specification<Notice> toSpecification() {
        Set<Specification<Notice>> specifications = new HashSet<>();
        specifications.add(toSpecification("subjectId", subjectId));
        return Specifications.and(specifications);
    }
}
