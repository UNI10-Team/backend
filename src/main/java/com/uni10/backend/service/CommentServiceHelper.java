package com.uni10.backend.service;

import com.uni10.backend.api.dto.AttachmentDTO;
import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.requests.CommentRequest;
import com.uni10.backend.entity.*;
import com.uni10.backend.repository.AttachmentRepository;
import com.uni10.backend.repository.CommentRepository;
import com.uni10.backend.repository.CourseRepository;
import com.uni10.backend.repository.EnrollRepository;
import com.uni10.backend.repository.SubjectRepository;
import com.uni10.backend.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Sends mails after a new comment is created or
 * one comment was accepted
 */
@Aspect
@Component
@AllArgsConstructor
public class CommentServiceHelper {

    private SecurityService securityService;
    private AttachmentRepository attachmentRepository;
    private SubjectRepository subjectRepository;
    private MailService mailService;
    private CommentRepository commentRepository;
    private CourseRepository courseRepository;
    private EnrollRepository enrollRepository;

    @Before("execution(* com.uni10.backend.service.CommentService.findAll(..)) && args(commentRequest)")
    public void findAll(final CommentRequest commentRequest) {
        if (!securityService.isUserInRole(Role.ROLE_SUBJECT_TEACHER)) {
            commentRequest.setAccepted(true);
            commentRequest.setUserId(securityService.getCurrentUser().getId());
        }

    }


    @Before("execution(* com.uni10.backend.service.CommentService.save(..)) && args(commentDTO)")
    public void save(final CommentDTO commentDTO) {
        if (securityService.isUserInRole(Role.ROLE_SUBJECT_TEACHER))
            commentDTO.setAccepted(true);
        else
            commentDTO.setAccepted(false);
        commentDTO.setUserId(securityService.getCurrentUser().getId())
                .setUsername(securityService.getCurrentUser().getUsername());
    }

    @Before("execution(* com.uni10.backend.service.CommentService.update(..)) && args(commentDTO, ..)")
    public void update(final CommentDTO commentDTO) {
        System.out.println("update");
        if (!securityService.isUserInRole(Role.ROLE_SUBJECT_TEACHER)) {
            commentDTO.setAccepted(false);
        }
    }

    //@Async
    //@AfterReturning("execution(* com.uni10.backend.service.CommentService.save(..)) && args(commentDTO)")
    public void sendNewCommentMail(final CommentDTO commentDTO) {
        System.out.println("sendNewCommentMail");
        long attachmentId = commentDTO.getAttachmentId();
        Subject subject;
        if (attachmentId != 0) {
            Attachment attachment = attachmentRepository.findById(attachmentId).get();
            subject = attachment.getCourse().getSubject();
        } else {
            subject = subjectRepository.findById(commentDTO.getSubjectId()).get();
        }
        mailService.sendNewCommentMail(0, subject, "en");

    }

    //@Async
    //@AfterReturning("execution(* com.uni10.backend.service.CommentService.update(..)) && args(commentDTO, ..)")
    public void sendNewAcceptMail(final CommentDTO commentDTO) {
        System.out.println("sendNewAcceptMail");
        val optional = commentRepository.findById(commentDTO.getId());
        Comment comment = optional.get();
        comment.setAccepted(true);
        comment = commentRepository.save(comment);
        mailService.sendNewAcceptMail(comment.getUser(), "en");
    }

    //@Async
    //@AfterReturning("execution (* com.uni10.backend.service.AttachmentService.save(..)) && args(attachmentDTO)")
    public void sendNewAttachmentMail(final AttachmentDTO attachmentDTO) {
        Course course = courseRepository.findById(attachmentDTO.getCourseId()).get();
        Subject subject = course.getSubject();
        List<User> students = new ArrayList<>();
        for (Enroll e : enrollRepository.findAllBySubjectId(subject.getId())) {
            students.add(e.getStudent());
        }
        mailService.sendNewAttachmentMail(students, "en");
    }
}
