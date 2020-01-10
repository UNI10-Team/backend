package com.uni10.backend.service;

import com.uni10.backend.mail.Sender;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.entity.User;
import com.uni10.backend.security.SecurityService;
import com.uni10.backend.security.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private Sender sender;
    private SecurityService securityService;
    private I18NTranslationService i18NTranslationService;

    public void sendNewCommentMail(long messageId, Subject subject, String language) {
        UserInfo student = securityService.getCurrentUser();
        User teacher = subject.getTeacher();
        sender.sendSimpleMessage(teacher.getEmail(),"New Comment", i18NTranslationService.getTranslationMessage(1,language));
    }
}
