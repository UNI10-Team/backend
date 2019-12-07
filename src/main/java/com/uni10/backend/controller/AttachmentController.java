package com.uni10.backend.controller;

import com.uni10.backend.annotations.AttachmentValid;
import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.api.requests.AttachmentRequest;
import com.uni10.backend.entity.Attachment;
import com.uni10.backend.service.AttachmentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("AttachmentController")
@RestController
@RequestMapping(value = "/attachments")
@AllArgsConstructor
public class AttachmentController {

    private AttachmentService attachmentService;

    @GetMapping
    public Page<Attachment> findAll(final AttachmentRequest attachmentRequest) {
        System.out.println(attachmentRequest.getDate());
        System.out.println(attachmentRequest.getTime());
        return attachmentService.findAll(attachmentRequest);
    }

    @PostMapping
    public AttachmentDTO save(@Valid @RequestBody final AttachmentDTO attachmentDTO) {
        return attachmentService.save(attachmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        attachmentService.deleteById(id);
    }
}
