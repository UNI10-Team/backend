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
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    @ApiOperation(value = "CourseController.findAll", notes = "Find all courses")
    public List<CourseDTO> findAll(CourseRequest courseRequest) {
        return courseService.findAll(courseRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "CourseController.findById", notes = "Find one course by id")
    public ResponseEntity<CourseDTO> findById(@PathVariable long id) {
        val optional = courseService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "CourseController.save", notes = "Save a new course")
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.save(courseDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "CourseController.update", notes = "Update an existent course")
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO subjectDTO, @PathVariable long id) {
        val optional = courseService.update(subjectDTO, id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "CourseController.deleteById", notes = "Delete an existent course")
    public ResponseEntity deleteById(@PathVariable long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
