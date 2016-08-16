package com.kp.errors;

import javax.management.RuntimeErrorException;

/**
 * Created by diman on 05.08.16.
 */
public final class EmailExistsException extends RuntimeException {
	public EmailExistsException(final String message){
		super(message);
	}
}
