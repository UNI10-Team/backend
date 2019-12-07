package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Time;

@ApiModel
@Data
@Accessors(chain = true)
public class ScheduleDTO {

    private long id;

    private String day;

    private Time fromTime;

    private Time toTime;

    private String room;

    private long teacherId;

    private long courseId;
}
