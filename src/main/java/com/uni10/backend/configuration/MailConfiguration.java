package com.uni10.backend.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@PropertySource("mail.properties")
public class MailConfiguration {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Value("${username}")
    private String username;

    @Value("${email}")
    private String email;

    @Value("${password}")
    private String password;

    @Value("${debug}")
    private  boolean debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("uni10@scs.ubbcluj.ro");
        mailSender.setPassword("2A$w4q2qq23131");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", debug);

        return mailSender;
    }
}
