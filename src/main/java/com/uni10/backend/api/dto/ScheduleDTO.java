package com.uni10.backend.api.dto;

import io.swagger.annotations.ApiModel;

import java.sql.Time;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ApiModel
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ScheduleDTO {

    private long id;

    private String day;

    private Time fromTime;

    private Time toTime;

    private String room;

    private long teacherId;

    private long courseId;
}
