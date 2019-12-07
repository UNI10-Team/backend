package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel
@Data
@Accessors(chain = true)
public class AttachmentDTO {

    private long id;

    private String data;

    private String name;

    private String type;

    private long courseId;

}
