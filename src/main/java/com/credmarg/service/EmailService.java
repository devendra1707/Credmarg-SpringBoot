package com.credmarg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	 @Autowired
	    private JavaMailSender emailSender;

	    public void sendTemplatedEmail(String to, String subject, String template, Object... args) {
	        String message = String.format(template, args);

	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(to);
	        mailMessage.setSubject(subject);
	        mailMessage.setText(message);

	        emailSender.send(mailMessage);
	    }
}