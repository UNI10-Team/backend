package com.uni10.backend.api.requests;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.Comment;
import io.swagger.annotations.ApiParam;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CommentRequest extends PagedRequest implements Filter<Comment> {

    @ApiParam(name = "attachmentId")
    private List<String> attachmentId = new ArrayList<>();

    @ApiParam(name = "subjectId")
    private List<String> subjectId = new ArrayList<>();

    @ApiParam(name = "isAccepted")
    @Setter
    private boolean isAccepted;


    @ApiParam(name = "userId")
    @Setter
    private long userId;

    @Override
    public Specification<Comment> toSpecification() {
        Specification<Comment> specification = byAttachment();
        if (specification != null) {
            specification = specification.and(bySubject());
        } else {
            specification = bySubject();
        }
        if (isAccepted) {
            if (specification != null) {
                specification = specification.and(byUsername().or(byAccepted()));
            } else {

                specification = byUsername().or(byAccepted());
            }
        }
        return specification;
    }

    private Specification<Comment> byAttachment() {
        return toSpecification("attachmentId", attachmentId);
    }

    private Specification<Comment> bySubject() {

        return toSpecification("subjectId", subjectId);
        //return toSpecification("attachment_course_subjectId", subjectId);
    }

    private Specification<Comment> byAccepted() {
        return toBoolSpecification("isAccepted", isAccepted);
    }

    private Specification<Comment> byUsername() {
        return toSpecification("userId", "" + userId);
    }
}
