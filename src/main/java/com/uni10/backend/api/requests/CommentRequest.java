package com.uni10.backend.api.requests;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.Comment;
import com.uni10.backend.entity.Course;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommentRequest  extends PagedRequest implements Filter<Comment> {

    @ApiParam(name = "attachmentId")
    private List<String> attachmentId = new ArrayList<>();

    @ApiParam(name = "isAccepted")
    @Setter
    private boolean isAccepted;

    @Override
    public Specification<Comment> toSpecification() {
        Set<Specification<Comment>> specifications = new HashSet<>();
        specifications.add(toSpecification("attachment_id", attachmentId));
        specifications.add(toBoolSpecification("isAccepted", isAccepted));
        return Specifications.and(specifications);
    }
}
