package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel
@Data
@Accessors(chain = true)
public class CommentDTO {

    private long id;

    private String text;

    private long userId;

    private String username;

    private long attachmentId;

    private boolean isAccepted;

}
