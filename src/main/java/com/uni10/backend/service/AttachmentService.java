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
        Attachment attachment = attachment(attachmentDTO);
        attachment = attachmentRepository.save(attachment);
        return attachmentDTO(attachment);
    }

    public void deleteById(final long id) {
        attachmentRepository.deleteById(id);
    }

    private static AttachmentDTO attachmentDTO(final Attachment attachment){
        return new AttachmentDTO()
                .setId(attachment.id())
                .setData(new String(attachment.data()))
                .setName(attachment.name())
                .setType(attachment.type())
                .setCourseId(attachment.courseId());
    }

    private static Attachment attachment(final AttachmentDTO attachmentDTO){
        return new Attachment()
                .id(0)
                .data(attachmentDTO.getData().getBytes())
                .name(attachmentDTO.getName())
                .type(attachmentDTO.getType())
                .courseId(attachmentDTO.getCourseId());
    }
}
