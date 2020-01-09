package com.uni10.backend.service;

import com.uni10.backend.entity.Sender;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.entity.User;
import com.uni10.backend.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private Sender sender;
    private SecurityService securityService;
    private I18NTranslationService i18NTranslationService;

    public void sendNewCommentMail(long messageId, Subject subject, String language) {
        User student = securityService.getCurrentUser();
        User teacher = subject.getTeacher();
        sender.sendSimpleMessage(teacher.getEmail(),"New Comment", i18NTranslationService.getTranslationMessage(1,language));
    }
}
