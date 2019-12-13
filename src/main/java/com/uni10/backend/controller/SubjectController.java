package com.uni10.backend.controller;


import com.uni10.backend.api.dto.SubjectDTO;
import com.uni10.backend.api.requests.SubjectRequest;
import com.uni10.backend.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "SubjectController")
@RestController
@RequestMapping("/subjects")
@AllArgsConstructor
public class SubjectController {

    private SubjectService subjectService;

    @GetMapping
    @ApiOperation(value = "SubjectController.findAll", notes = "Find all Subjects")
    public ResponseEntity<Page<SubjectDTO>> findAll(final SubjectRequest personRequest) {
        return ResponseEntity.ok(subjectService.findAll(personRequest));
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation(value = "SubjectController.findById", notes = "Find one Subject by id")
    public ResponseEntity<SubjectDTO> findById(@PathVariable final long id) {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "SubjectController.save", notes = "Save a new subject")
    public ResponseEntity<SubjectDTO> save(@Valid @RequestBody final SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.save(subjectDTO));
    }

    @PutMapping("/{id:[0-9]+}")
    @ApiOperation(value = "PersonController.update", notes = "Update an existent subject")
    public ResponseEntity<SubjectDTO> update(@Valid @RequestBody final SubjectDTO subjectDTO,
                                             @PathVariable final long id) {
        return ResponseEntity.ok(subjectService.update(subjectDTO, id));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @ApiOperation(value = "SubjectController.deleteById", notes = "Delete an existent subject")
    public ResponseEntity deleteById(@PathVariable final long id) {
        subjectService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

