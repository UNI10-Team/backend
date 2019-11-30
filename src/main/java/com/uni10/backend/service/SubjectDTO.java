package com.uni10.backend.service;

import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.objects.annotations.Getter;

@Getter
@Builder
@ApiModel
public class SubjectDTO {
    private String name;
    private long teacherId;
}
