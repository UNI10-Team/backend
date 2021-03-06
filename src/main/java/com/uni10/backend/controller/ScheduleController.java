package com.uni10.backend.controller;

import com.uni10.backend.api.dto.ScheduleDTO;
import com.uni10.backend.api.requests.ScheduleRequest;
import com.uni10.backend.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "ScheduleController")
@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;

    @GetMapping
    @ApiOperation(value = "ScheduleController.findAll", notes = "Find all schedule")
    public Page<ScheduleDTO> findAll(final ScheduleRequest scheduleRequest) {
        return scheduleService.findAll(scheduleRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "ScheduleController.findById", notes = "Find one schedule by id")
    public ResponseEntity<ScheduleDTO> findById(@PathVariable("id") final long id) {
        return ResponseEntity.of(scheduleService.findById(id));
    }

    @PostMapping
    @Secured({"ROLE_SUBJECT_TEACHER"})
    @ApiOperation(value = "ScheduleController.save", notes = "Save a new schedule")
    public ResponseEntity<ScheduleDTO> save(@Valid @RequestBody final ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.save(scheduleDTO));
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_COURSE_TEACHER"})
    @ApiOperation(value = "ScheduleController.update", notes = "Update an existent schedule")
    public ResponseEntity<ScheduleDTO> update(@Valid @RequestBody final ScheduleDTO scheduleDTO,
                                              @PathVariable("id") final long id) {
        return ResponseEntity.ok(scheduleService.update(scheduleDTO, id));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_SUBJECT_TEACHER"})
    @ApiOperation(value = "ScheduleController.deleteById", notes = "Delete an existent schedule")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
