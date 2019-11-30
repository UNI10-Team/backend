package com.uni10.backend.service;

import io.swagger.annotations.ApiModel;

@Getter
@Builder
@ApiModel
public class CourseDTO {
    private String type;
    private long subjectId;
}
