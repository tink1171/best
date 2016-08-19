package com.kp.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String Email_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void initialize(final ValidEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String Email, final ConstraintValidatorContext context) {
		return (validateEmail(Email));
	}

	private boolean validateEmail(final String Email) {
		pattern = Pattern.compile(Email_PATTERN);
		matcher = pattern.matcher(Email);
		return matcher.matches();
	}
}