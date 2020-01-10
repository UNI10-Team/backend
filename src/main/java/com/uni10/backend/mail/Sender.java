package com.uni10.backend.mail;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class Sender {

    private final JavaMailSender mailSender;


    public Sender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    public void sendSimpleMessage(String to, String subject, String text, String ... args) {
        text = String.format(text, (Object[]) args);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendMessageWithAttachments(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        File folder = new File("");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File value : files) {
                FileSystemResource file = new FileSystemResource(value);
                helper.addAttachment(file.getFilename(), file);
            }
        }
        mailSender.send(message);
    }

    private String readHTML() throws IOException {
        StringBuilder HTML = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cucuis\\personal\\university\\pc\\mail\\src\\main\\resources\\animations.html"));
        String line = reader.readLine();
        while (line != null) {
            HTML.append(line);
            line = reader.readLine();
        }
        return HTML.toString();
    }

    public void sendMessageWithHTML(String to, String subject, String html) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);
        mailSender.send(message);
    }

}
