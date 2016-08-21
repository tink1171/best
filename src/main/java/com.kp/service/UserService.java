package com.kp.service;

import com.kp.dto.UserDto;
import com.kp.errors.EmailExistsException;
import com.kp.model.user.User;
import com.kp.model.verification_token.VerificationToken;

import java.util.List;

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

	User findByUsername(String username);

	User findById(long id);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);
}
