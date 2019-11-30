package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ApiModel
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CommentDTO {

    private long id;

    private String text;

    private long userId;

    private long attachmentId;

}
