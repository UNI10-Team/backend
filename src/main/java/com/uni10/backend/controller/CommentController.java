package com.uni10.backend.controller;

import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.requests.CommentRequest;
import com.uni10.backend.api.requests.SubjectRequest;
import com.uni10.backend.entity.Comment;
import com.uni10.backend.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Api(value = "SubjectController")
@RestController
@RequestMapping("/courses/{courseId}/attachments/{attachmentId}/comments")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping
    @ApiOperation(value = "CommentController.findAll", notes = "Find all comments")
    public Page<CommentDTO> findAll(CommentRequest commentRequest, @PathVariable(name = "attachmentId") final long attachmentId) {
        return commentService.findAll(commentRequest, attachmentId);
    }

    @PostMapping
    @ApiOperation(value = "CommentController.save", notes = "Save a new comment")
    public ResponseEntity<CommentDTO> save(@PathVariable(name = "attachmentId") final long attachmentId, @Valid @RequestBody CommentDTO commentDTO) {
        val optional = commentService.save(commentDTO, attachmentId);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "CommentController.update", notes = "Update an existent comment")
    public ResponseEntity<CommentDTO> update(@Valid @RequestBody CommentDTO subjectDTO, @PathVariable long id) {
        val optional = commentService.update(subjectDTO, id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "CommentController.deleteById", notes = "Delete an existent comment")
    public ResponseEntity deleteById(@PathVariable long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
