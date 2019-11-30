package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel
public class CommentDTO {

    private String text;
    private long userId;
    private long attachmentId;

}
