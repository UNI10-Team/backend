package com.uni10.backend.controller;

import com.uni10.backend.api.dto.ScheduleDTO;
import com.uni10.backend.api.requests.ScheduleRequest;
import com.uni10.backend.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "ScheduleController")
@RestController
@RequestMapping("/courses/{courseId}/schedules")
@AllArgsConstructor
public class ScheduleController {
    private ScheduleService scheduleService;

    @GetMapping
    @ApiOperation(value = "ScheduleController.findAll", notes = "Find all schedule")
    public Page<ScheduleDTO> findAll(@PathVariable("courseId") final long  courseId, final ScheduleRequest scheduleRequest) {
        return scheduleService.findAll(courseId, scheduleRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ScheduleController.findById", notes = "Find one schedule by id")
    public ResponseEntity<ScheduleDTO> findById(@PathVariable final long id) {
        val optional = scheduleService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "ScheduleController.save", notes = "Save a new schedule")
    public ResponseEntity<ScheduleDTO> save(@Valid @RequestBody final ScheduleDTO attendanceDTO) {
        return ResponseEntity.ok(scheduleService.save(attendanceDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "ScheduleController.update", notes = "Update an existent schedule")
    public ResponseEntity<ScheduleDTO> update(@Valid @RequestBody final ScheduleDTO attendanceDTO, @PathVariable final long id) {
        val optional = scheduleService.update(attendanceDTO, id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "ScheduleController.deleteById", notes = "Delete an existent schedule")
    public ResponseEntity deleteById(@PathVariable final long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
