package com.uni10.backend.controller;
import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.requests.CourseRequest;
import com.uni10.backend.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CourseController")
@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    @ApiOperation(value = "CourseController.findAll", notes = "Find all courses")
    public ResponseEntity<Page<CourseDTO>> findAll(CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.findAll(courseRequest));
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "CourseController.findById", notes = "Find one course by id")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") final long id) {
        return ResponseEntity.of(courseService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "CourseController.save", notes = "Save a new course")
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody final CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.save(courseDTO));
    }

    @PutMapping("/{id:[0-9]+}")
    @ApiOperation(value = "CourseController.update", notes = "Update an existent course")
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody final CourseDTO courseDTO,
                                            @PathVariable("id") final long id) {
        return ResponseEntity.ok(courseService.update(courseDTO, id));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @ApiOperation(value = "CourseController.deleteById", notes = "Delete an existent course")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
