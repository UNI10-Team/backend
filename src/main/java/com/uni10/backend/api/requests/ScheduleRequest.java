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
    @ApiParam(name = "fromTime")
    private List<String> fromTime = new ArrayList<>();
    @ApiParam(name = "toTime")
    private List<String> toTime = new ArrayList<>();
    @ApiParam(name = "room")
    private List<String> room = new ArrayList<>();
    @ApiParam(name = "teacherId")
    private List<String> teacherId = new ArrayList<>();

    @Override
    public Specification<Schedule> toSpecification() {
        Set<Specification<Schedule>> specifications = new HashSet<>();
        specifications.add(toSpecification("day", day));
        specifications.add(toSpecification("fromTime", fromTime));
        specifications.add(toSpecification("toTime", toTime));
        specifications.add(toSpecification("room", room));
        specifications.add(toSpecification("teacherId", teacherId));
        return Specifications.and(specifications);
    }
}
