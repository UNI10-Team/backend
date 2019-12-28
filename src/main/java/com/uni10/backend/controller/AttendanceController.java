package com.uni10.backend.controller;

import com.uni10.backend.api.dto.AttendanceDTO;
import com.uni10.backend.api.requests.AttendanceRequest;
import com.uni10.backend.service.AttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "AttendanceController")
@RestController
@RequestMapping("/attendance")
@AllArgsConstructor
public class AttendanceController {
    private AttendanceService attendanceService;

    @GetMapping
    @ApiOperation(value = "AttendanceController.findAll", notes = "Find all requests")
    public Page<AttendanceDTO> findAll(final AttendanceRequest attendanceRequest) {
        return attendanceService.findAll(attendanceRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "AttendanceController.findById", notes = "Find one attendance by id")
    public ResponseEntity<AttendanceDTO> findById(@PathVariable final long id) {
        return ResponseEntity.of(attendanceService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "AttendanceController.save", notes = "Save a new attendance")
    public ResponseEntity<AttendanceDTO> save(@Valid @RequestBody final AttendanceDTO attendanceDTO) {
        return ResponseEntity.ok(attendanceService.save(attendanceDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "AttendanceController.update", notes = "Update an existent attendance")
    public ResponseEntity<AttendanceDTO> update(@Valid @RequestBody final AttendanceDTO attendanceDTO, @PathVariable final long id) {
        return ResponseEntity.ok(attendanceService.update(attendanceDTO, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "AttendanceController.deleteById", notes = "Delete an existent attendance")
    public ResponseEntity deleteById(@PathVariable final long id) {
        attendanceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
