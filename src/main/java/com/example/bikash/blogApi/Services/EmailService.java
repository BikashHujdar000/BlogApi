package com.example.bikash.blogApi.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public  void sendEmail(String to , String subject , String body) throws MessagingException{


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lalbhikhan000@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);



    }





}
//public void sendEmail(String to, String subject, String body) throws MessagingException {
//    MimeMessage message = mailSender.createMimeMessage();
//    MimeMessageHelper helper = new MimeMessageHelper(message);
//
//    helper.setTo(to);
//    helper.setSubject(subject);
//    helper.setText(body);
//
//    mailSender.send(message);