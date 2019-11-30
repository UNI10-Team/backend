package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;

import java.sql.Time;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel
public class ScheduleDTO {
    private long courseId;
    private String day;
    private Time fromTime;
    private Time toTime;
    private String room;
}
