package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@ApiModel
public class AttachmentDTO {

    private long id;

    private String data;

    private String name;

    private String type;

    private long courseId;

    private Set<CommentDTO> comments;
}
