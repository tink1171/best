package com.kp.controllers;

import com.kp.dto.UserDto;
import com.kp.errors.EmailExistsException;
import com.kp.events.OnRegistrationCompleteEvent;
import com.kp.model.model_of_user.User;
import com.kp.model.verification_token.VerificationToken;
import com.kp.repository.UserRepository;
import com.kp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by diman on 05.08.16.
 */
@Controller
public class RegistrationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private UserService service;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView showRegistrationForm(@ModelAttribute("user") UserDto accountDto) {
		LOGGER.debug(" view form for registartion ");
		return new ModelAndView("registration","user",new UserDto());
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public String getUsers(){
		return (userRepository.findAll().toString());
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final UserDto accountDto, BindingResult result, HttpServletRequest request, Errors errors) {
		LOGGER.debug("Registering user account with information: {}", accountDto);

		User registered = new User();
		if(!errors.hasErrors()){
			registered = createNewUserAccount(accountDto, result);
		}
		if (registered == null) {
			LOGGER.debug("Oshibka !!!");
			return new ModelAndView("registration", "user", accountDto);
		}
		try {
			final String appUrl = request.getContextPath();
			LOGGER.debug("URL: " + appUrl);
			applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
		} catch (final Exception ex) {
			LOGGER.warn("Unable to register user", ex);
			return new ModelAndView("registration", "user", accountDto);
		}
		return new ModelAndView("registration", "user", accountDto);
	}

	private User createNewUserAccount(UserDto accountDto, BindingResult bindingResult){
		User registered = null;
		try{
			registered = service.registerNewUserAccount(accountDto);
		}
		catch (EmailExistsException exception){
			return null;
		}
		return registered;
	}

	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration (final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {
		LOGGER.debug("confirm registartion for enable user acc !!!");
		Locale locale = request.getLocale();
		LOGGER.debug("TOKEN : " + token);
		VerificationToken verificationToken = service.getVerificationToken(token);
		if (verificationToken == null) {
			String message = messages.getMessage("auth.message.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "redirect:/badUser";
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
			return "redirect:/badUser";
		}

		user.setEnabled(true);
		service.saveRegisteredUser(user);
		LOGGER.debug("INFO : " + user.toString());
		return "hello";
	}

	@RequestMapping(value = "/badUser", method = RequestMethod.GET)
	public String isBadUser(final Model model){
		model.addAttribute("message", "Error, try again. Diman dont remember that regConfirm can return reason which causes this error by class MESSAGES!");
		return "badUser";
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ResponseBody
	public String emailAdmin(final Authentication authentication){
		return authentication.getName();
	}
}
