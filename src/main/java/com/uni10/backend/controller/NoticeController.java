package com.uni10.backend.controller;

import com.uni10.backend.api.dto.NoticeDTO;
import com.uni10.backend.api.requests.NoticeRequest;
import com.uni10.backend.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "NoticeController")
@RestController
@RequestMapping("/notices")
@AllArgsConstructor
public class NoticeController {
    private NoticeService noticeService;

    @GetMapping
    @ApiOperation(value = "NoticeController.findAll", notes = "Find all notices")
    public Page<NoticeDTO> findAll(NoticeRequest noticeRequest) {
        return noticeService.findAll(noticeRequest);
    }

    @PostMapping
    @ApiOperation(value = "NoticeController.save", notes = "Save a new notice")
    public ResponseEntity<NoticeDTO> save(@Valid @RequestBody NoticeDTO noticeDTO) {
        return ResponseEntity.ok(noticeService.save(noticeDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "NoticeController.deleteById", notes = "Delete an existent notice")
    public ResponseEntity deleteById(@PathVariable long id) {
        noticeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}