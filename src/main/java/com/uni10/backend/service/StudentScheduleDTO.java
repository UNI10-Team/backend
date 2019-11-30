package com.uni10.backend.service;

import io.swagger.annotations.ApiModel;


@Getter
@Builder
@ApiModel
public class StudentScheduleDTO {
    private long studentId;
    private long scheduleId;
}

