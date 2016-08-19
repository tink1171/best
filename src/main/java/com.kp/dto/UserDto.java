package com.kp.dto;

import com.kp.validation.PasswordMatches;
import com.kp.validation.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by diman on 04.08.16.
 */
@PasswordMatches
public class UserDto {

	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;

	@ValidEmail
	@NotNull
	@NotEmpty
	private String Email;

	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;

	public UserDto() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	@Override
	public String toString() {
		return "UserDto{" +
						"firstName='" + firstName + '\'' +
						", lastName='" + lastName + '\'' +
						", email='" + Email + '\'' +
						", password='" + password + '\'' +
						", matchingPassword='" + matchingPassword + '\'' +
//						", role=" + role +
						'}';
	}
}