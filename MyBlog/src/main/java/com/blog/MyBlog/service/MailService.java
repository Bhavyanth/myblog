package com.blog.MyBlog.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.blog.MyBlog.exceptions.SpringPostException;
import com.blog.MyBlog.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MailService {
	
	private JavaMailSender mailSender;
	private MailContentBuilder contentBuilder;
	
	@Async
	public  void sendMail (NotificationEmail email){
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			 MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			 messageHelper.setFrom("bhavyanth02@gmail.com");
			 messageHelper.setTo(email.getRecipient());
			 messageHelper.setSubject(email.getSubject());
			 messageHelper.setText(contentBuilder.build(email.getBody()));
		};
		try {
			mailSender.send(messagePreparator);
			
		} catch (MailException e) {
			throw new SpringPostException("Exception occured while sending mail"+ e);
		}
	}
	
}
