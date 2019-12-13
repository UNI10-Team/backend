package com.uni10.backend.controller;
import com.uni10.backend.api.dto.CourseDTO;
import com.uni10.backend.api.requests.CourseRequest;
import com.uni10.backend.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CourseController")
@RestController
@RequestMapping("subjects/{subjectId:[0-9]+}/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    @ApiOperation(value = "CourseController.findAll", notes = "Find all courses")
    public ResponseEntity<List<CourseDTO>> findAll(CourseRequest courseRequest,
                                   @PathVariable("subjectId") final long subjectId) {
        return ResponseEntity.ok(courseService.findAll(courseRequest, subjectId));
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "CourseController.findById", notes = "Find one course by id")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") final long id,
                                              @PathVariable("subjectId") final long subjectId) {
        return ResponseEntity.ok(courseService.findById(id, subjectId));
    }

    @PostMapping
    @ApiOperation(value = "CourseController.save", notes = "Save a new course")
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody final CourseDTO courseDTO,
                                          @PathVariable("subjectId") final long subjectId) {
        return ResponseEntity.ok(courseService.save(courseDTO, subjectId));
    }

    @PutMapping("/{id:[0-9]+}")
    @ApiOperation(value = "CourseController.update", notes = "Update an existent course")
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody final CourseDTO courseDTO,
                                            @PathVariable("id") final long id,
                                            @PathVariable("subjectId") final long subjectId) {
        return ResponseEntity.ok(courseService.update(courseDTO, id, subjectId));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @ApiOperation(value = "CourseController.deleteById", notes = "Delete an existent course")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final long id,
                                     @PathVariable("subjectId") final long subjectId) {
        courseService.deleteById(id, subjectId);
        return ResponseEntity.ok().build();
    }

}
