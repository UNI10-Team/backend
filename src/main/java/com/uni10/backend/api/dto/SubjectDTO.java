package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel
@Data
@Accessors(chain = true)
public class SubjectDTO {

    private long id;

    private String name;

    private long teacherId;

    private int year;
}
