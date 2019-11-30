package com.uni10.backend.service;

import io.swagger.annotations.ApiModel;

import java.sql.Time;

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
