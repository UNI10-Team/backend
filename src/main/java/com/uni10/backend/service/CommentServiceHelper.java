package com.uni10.backend.service;

import com.uni10.backend.api.dto.CommentDTO;
import com.uni10.backend.api.requests.CommentRequest;
import com.uni10.backend.entity.Comment;
import com.uni10.backend.entity.Role;
import com.uni10.backend.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Sends mails after a new comment is created or
 * one comment was accepted
 */
@Aspect
@Component
@AllArgsConstructor
public class CommentServiceHelper {

    private SecurityService securityService;

    @Before("execution(* com.uni10.backend.service.CommentService.findAll(..)) && args(commentRequest)")
    public void findAll(final CommentRequest commentRequest) {
        if (!securityService.isUserInRole(Role.ROLE_SUBJECT_TEACHER)) {
            commentRequest.setAccepted(true);
        }

    }

    @Async
    @AfterReturning("execution(* com.uni10.backend.service.CommentService.save(..)) && args(commentDTO)")
    public void sendNewCommentMail(final CommentDTO commentDTO) {
        System.out.println("sendNewCommentMail");
        /*
        Attachment att = comment.getAttachment();
        Subject subject = att.course().getSubject();
        mailService.sendNewCommentMail(0,subject,"en");
         */
    }

    @Before("execution(* com.uni10.backend.service.CommentService.update(..)) && args(commentDTO, ..)")
    public void update(final CommentDTO commentDTO) {
        System.out.println("update");
        if (!securityService.isUserInRole(Role.ROLE_SUBJECT_TEACHER)) {
            commentDTO.setAccepted(false);
        }
    }

    @Async
    @AfterReturning("execution(* com.uni10.backend.service.CommentService.update(..)) && args(commentDTO, ..)")
    public void sendNewAcceptMail(final CommentDTO commentDTO) {
        System.out.println("sendNewAcceptMail");
        /*
        val optional = commentRepository.findById(id);
        Comment comment = optional.get();
        comment.setAccepted(true);
        comment = commentRepository.save(comment);
        mailService.sendNewAcceptMail(comment.getUser(),"en");
         */
    }
}
