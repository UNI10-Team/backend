package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel
public class StudentScheduleDTO {
    private long studentId;
    private long scheduleId;
}

