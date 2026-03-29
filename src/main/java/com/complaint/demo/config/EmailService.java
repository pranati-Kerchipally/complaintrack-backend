package com.complaint.demo.config;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.complaint.demo.model.Complaint;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendComplaintEmail(Complaint complaint) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("sweetytest25@gmail.com");
        message.setSubject("New Complaint 🚨");

        message.setText(
            "Student: " + complaint.getStudentName() + "\n" +
            "Category: " + complaint.getCategory() + "\n" +
            "Description: " + complaint.getDescription()
        );

        mailSender.send(message);
    }

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}