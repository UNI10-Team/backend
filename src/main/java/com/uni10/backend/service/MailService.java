package com.uni10.backend.service;

import com.uni10.backend.entity.Course;
import com.uni10.backend.mail.Sender;
import com.uni10.backend.entity.Subject;
import com.uni10.backend.entity.User;
import com.uni10.backend.security.SecurityService;
import com.uni10.backend.security.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MailService {

    private Sender sender;
    private SecurityService securityService;
    private I18NTranslationService i18NTranslationService;
    private EnrollService enrollService;

    public void sendNewCommentMail(long messageId, Subject subject, String language) {
        User teacher = subject.getTeacher();
        sender.sendSimpleMessage(teacher.getEmail(),
                "New Comment",
                i18NTranslationService.getTranslationMessage(1, language),
                subject.getName(),
                securityService.getCurrentUser().getUsername()
        );
    }

    /**
     * user - The user that sent the comment
     * Sends email to the student to inform that his comment was accepted
     */
    public void sendNewAcceptMail(User user, String language) {
        sender.sendSimpleMessage(user.getEmail(), "Comment Accepted", i18NTranslationService.getTranslationMessage(2, language));
    }

    /**
     * students - list of students enrolled to the subject, where the attachment was posted
     * Sends email to inform the students about the attachment
     */
    public void sendNewAttachmentMail(List<User> students, String language) {
        for (User student : students)
            sender.sendSimpleMessage(student.getEmail(), "New Attachment", i18NTranslationService.getTranslationMessage(3, language));

    }
}
