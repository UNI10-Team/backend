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
    public Page<SubjectDTO> findAll(SubjectRequest personRequest) {
        return subjectService.findAll(personRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "SubjectController.findById", notes = "Find one Subject by id")
    public ResponseEntity<SubjectDTO> findById(@PathVariable long id) {
        val optional = subjectService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "SubjectController.save", notes = "Save a new subject")
    public ResponseEntity<SubjectDTO> save(@Valid @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.save(subjectDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "PersonController.update", notes = "Update an existent subject")
    public ResponseEntity<SubjectDTO> update(@Valid @RequestBody SubjectDTO subjectDTO, @PathVariable long id) {
        val optional = subjectService.update(subjectDTO, id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "SubjectController.deleteById", notes = "Delete an existent subject")
    public ResponseEntity deleteById(@PathVariable long id) {
        subjectService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

