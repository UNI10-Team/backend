package com.uni10.backend.api.requests;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.Schedule;
import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ScheduleRequest extends PagedRequest implements Filter<Schedule> {

    @ApiParam(name = "day")
    private List<String> day = new ArrayList<>();

    @ApiParam(name = "start")
    private String start;

    @ApiParam(name = "end")
    private String end;

    @ApiParam(name = "room")
    private List<String> room = new ArrayList<>();

    @ApiParam(name = "course")
    private List<String> course = new ArrayList<>();

    @ApiParam(name = "teacher")
    private List<String> teacher = new ArrayList<>();

    @Override
    public Specification<Schedule> toSpecification() {
        Set<Specification<Schedule>> specifications = new HashSet<>();
        specifications.add(toSpecification("day", day));
        specifications.add(toTimeSpecification("startAt", start));
        specifications.add(toTimeSpecification("endAt", end));
        specifications.add(toSpecification("room", room));
        specifications.add(toSpecification("course_id", course));
        specifications.add(toSpecification("teacher_id", teacher));
        return Specifications.and(specifications);
    }
}
