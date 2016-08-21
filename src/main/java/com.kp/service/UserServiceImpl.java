package com.kp.service;

import com.kp.dto.UserDto;
import com.kp.errors.EmailExistsException;
import com.kp.errors.UserAlreadyExistException;
import com.kp.model.user.User;
import com.kp.model.verification_token.VerificationToken;
import com.kp.repository.RoleRepository;
import com.kp.repository.UserRepository;
import com.kp.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by diman on 15.08.16.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Override
	public User findByEmail(String Email){
		User user = repository.findByEmail(Email);
		return user;
	}

	@Override
	public 	User findByUsername(String username){
		User user = repository.findByUsername(username);
		return user;
	}

	@Override
	public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
		if (EmailExist(accountDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
		}
		final User user = new User();

		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setPassword(accountDto.getPassword());
		user.setEmail(accountDto.getEmail());

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		return repository.save(user);
	}

	@Override
	public void createVerificationTokenForUser(final User user,final String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	@Override
	public User getUser(final String verificationToken) {
		final User user = tokenRepository.findByToken(verificationToken).getUser();
		return user;
	}

	@Override
	public void saveRegisteredUser(User user) {
		repository.save(user);
	}

	@Override
	public VerificationToken getVerificationToken(final String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	private boolean EmailExist(String Email) {
		User user = repository.findByEmail(Email);
		if (user != null) {
			return true;
		}
		return false;
	}

	public List<User> findAllUsers() {
		return repository.findAll();
	}

	public User findById(long id) {
		return repository.findById(id);
	}

	public void saveUser(User user) {
		repository.save(user);
	}

	public void updateUser(User user) {
		repository.save(user);
	}

	public void deleteUserById(long id) {
		repository.delete(id);
	}

	public boolean isUserExist(User user) {
		return (repository.findByUsername(user.getUsername())!=null);
	}

	public void deleteAllUsers(){
		repository.deleteAll();
	}
}
