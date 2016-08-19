package com.kp.service;

import com.kp.dto.UserDto;
import com.kp.errors.EmailExistsException;
import com.kp.model.user.User;
import com.kp.model.verification_token.VerificationToken;

/**
 * Created by diman on 04.08.16.
 */
public interface UserService {
	User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
	void createVerificationTokenForUser(User user, String token);
	User findByEmail(String Email);
	User getUser(String verificationToken);
	void saveRegisteredUser(User user);
	VerificationToken getVerificationToken(String VerificationToken);
}
