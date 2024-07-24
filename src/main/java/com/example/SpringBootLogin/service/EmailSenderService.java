package com.example.SpringBootLogin.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	private SimpleMailMessage message = new SimpleMailMessage();
//	@Autowired
//	public void setMailMessage(SimpleMailMessage message) {
//		this.message = message;
//	}
	public void sendMessage(String emailTo, String body, String subject) {
		
		message.setFrom("nidhidilesh@gmail.com");
		message.setTo(emailTo);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		System.out.println("Email sent successfully");
	}
}
