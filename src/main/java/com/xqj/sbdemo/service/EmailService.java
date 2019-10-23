package com.xqj.sbdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderName;

    public synchronized void sendSimpleEmail(String email, String title, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderName);
        mailMessage.setTo(email);
        mailMessage.setSubject(title);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
