package com.uni10.backend.api.requests;

import com.uni10.backend.specifications.Specifications;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.Attendance;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class AttendanceRequest extends PagedRequest implements Filter<Attendance> {
    @ApiParam(name = "studentId")
    private List<String> studentId = new ArrayList<>();
    @ApiParam(name = "scheduleId")
    private List<String> scheduleId = new ArrayList<>();

    @Override
    public Specification<Attendance> toSpecification() {
        Set<Specification<Attendance>> specifications = new HashSet<>();
        specifications.add(toSpecification("studentId", studentId));
        specifications.add((toSpecification("scheduleId",scheduleId)));
        return Specifications.and(specifications);
    }
}
