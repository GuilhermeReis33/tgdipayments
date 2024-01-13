package com.tgdipayments.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void EnviarEmail(String toEmail,
                            String subject,
                            String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("emailservice867@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}
