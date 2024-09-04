package com.softpro.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendRegistrationEmail(String toEmail, String pass, String name) {
    	String subject= "Registration Successful";
    	String text = "Hello dear "+name+", \n Your Registration is successfull, Now you can Login thourgh your Email ID and Password that you have created during registration\n Your User ID = "+toEmail + "\n and your Password = "+pass;                                                                                           
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);


}
}