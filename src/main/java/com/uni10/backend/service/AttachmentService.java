package com.uni10.backend.service;

import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.api.requests.AttachmentRequest;
import com.uni10.backend.entity.Attachment;
import com.uni10.backend.entity.Course;
import com.uni10.backend.repository.AttachmentRepository;
import com.uni10.backend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttachmentService {

    private AttachmentRepository attachmentRepository;
    private MailService mailService;
    private EnrollService enrollService;
    private CourseRepository courseRepository;

    public Page<Attachment> findAll(final AttachmentRequest attachmentRequest) {
        return attachmentRepository.findAll(PageRequest.of(0, 5));
    }

    public AttachmentDTO save(final AttachmentDTO attachmentDTO) {
        Attachment attachment = attachment(attachmentDTO);
        attachment.setCourseId(findFirstWithNoAttachment(attachmentDTO.getCourseId(), attachmentDTO.getCourseType()));
        attachment = attachmentRepository.save(attachment);
        //sends email to the students enrolled
        //mailService.sendNewAttachmentMail(enrollService.getAllStudent(attachment.course().getSubjectId()),"en");
        return attachmentDTO(attachment);
    }

    private long findFirstWithNoAttachment(long subjectId, String type){
        return courseRepository.findAllBySubjectIdAndType(subjectId, type)
                .stream()
                .filter(course -> course.getAttachment() == null)
                .sorted((o1, o2) -> (int)(o1.getId() - o2.getId()))
                .findFirst().get().getId();
    }

    public void deleteById(final long id) {
        attachmentRepository.deleteById(id);
    }


    public Optional<byte[]> findById(final long id) {
        return attachmentRepository.findById(id).map(Attachment::getData);
    }

    private static AttachmentDTO attachmentDTO(final Attachment attachment) {
        return new AttachmentDTO()
                .setId(attachment.getId())
                //.setData(new String(attachment.getData()))
                .setName(attachment.getName())
                .setType(attachment.getType())
                .setCourseId(attachment.getCourseId());
    }

    private static Attachment attachment(final AttachmentDTO attachmentDTO) {
        return new Attachment()
                .setId(0)
                .setData(attachmentDTO.getData().getBytes())
                .setName(attachmentDTO.getName())
                .setType(attachmentDTO.getType())
                .setCourseId(attachmentDTO.getCourseId());
    }
}
