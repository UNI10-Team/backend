package com.uni10.backend.service;

import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.api.requests.AttachmentRequest;
import com.uni10.backend.entity.Attachment;
import com.uni10.backend.repository.AttachmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttachmentService {

    private AttachmentRepository attachmentRepository;

    public Page<Attachment> findAll(final AttachmentRequest attachmentRequest) {
        return attachmentRepository.findAll(PageRequest.of(0, 5));
    }

    public AttachmentDTO save(final AttachmentDTO attachmentDTO){
        Attachment attachment = attachment(attachmentDTO, 0);
        attachment = attachmentRepository.save(attachment);
        return attachmentDTO(attachment);
    }

    public void deleteById(final long id) {
        attachmentRepository.deleteById(id);
    }

    private static AttachmentDTO attachmentDTO(final Attachment attachment){
        return AttachmentDTO
                .builder()
                .id(attachment.getId())
                .data(new String(attachment.getData()))
                .name(attachment.getName())
                .type(attachment.getType())
                .courseId(attachment.getCourseId())
                .build();
    }

    private static Attachment attachment(final AttachmentDTO attachmentDTO, final long id){
        return Attachment
                .builder()
                .id(id)
                .data(attachmentDTO.getData().getBytes())
                .name(attachmentDTO.getName())
                .type(attachmentDTO.getType())
                .courseId(attachmentDTO.getCourseId())
                .build();
    }
}
