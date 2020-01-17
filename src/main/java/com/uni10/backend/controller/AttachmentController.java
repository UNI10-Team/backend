package com.uni10.backend.controller;

import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.api.requests.AttachmentRequest;
import com.uni10.backend.entity.Attachment;
import com.uni10.backend.service.AttachmentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("AttachmentController")
@RestController
@RequestMapping(value = "/attachments")
@AllArgsConstructor
@CrossOrigin
public class AttachmentController {

    private AttachmentService attachmentService;

    @GetMapping
    public Page<Attachment> findAll(final AttachmentRequest attachmentRequest) {
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


    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> findById(@PathVariable long id) {

        val attachment = attachmentService.findById(id);
        if (attachment.isPresent()){
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(attachment.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
