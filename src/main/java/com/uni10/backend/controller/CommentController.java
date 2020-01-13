package com.uni10.backend.controller;

import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.requests.CommentRequest;
import com.uni10.backend.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "SubjectController")
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping
    @ApiOperation(value = "CommentController.findAll", notes = "Find all comments")
    public Page<CommentDTO> findAll(CommentRequest commentRequest) {
        return commentService.findAll(commentRequest);
    }

    @PostMapping
    @ApiOperation(value = "CommentController.save", notes = "Save a new comment")
    public ResponseEntity<CommentDTO> save(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.save(commentDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "CommentController.update", notes = "Update an existent comment")
    public ResponseEntity<CommentDTO> update(@Valid @RequestBody CommentDTO commentDTO, @PathVariable long id) {
        return ResponseEntity.ok(commentService.update(commentDTO, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "CommentController.deleteById", notes = "Delete an existent comment")
    public ResponseEntity deleteById(@PathVariable long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
