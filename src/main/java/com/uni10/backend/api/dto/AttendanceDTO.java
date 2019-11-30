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
public class AttendanceDTO {

    private long id;

    private long studentId;

    private long scheduleId;

}

