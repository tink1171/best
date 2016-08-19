package com.kp.listeners;

import com.kp.events.OnRegistrationCompleteEvent;
import com.kp.model.user.User;
import com.kp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


import java.util.UUID;


/**
 * Created by diman on 08.08.16.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private UserService service;
	@Autowired
	private MessageSource messages;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();

		service.createVerificationTokenForUser(user, token);

		String recipientAddress = user.getEmail();
		String subject = "Registration Confirmation";
		String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
		String message = messages.getMessage("message.regSucc", null, event.getLocale());
		SimpleMailMessage Email = new SimpleMailMessage();
		Email.setTo(recipientAddress);
		Email.setSubject(subject);
		Email.setText(message + /*" rn" +*/ "http://localhost:8080" + confirmationUrl);
		mailSender.send(Email);
	}
}
