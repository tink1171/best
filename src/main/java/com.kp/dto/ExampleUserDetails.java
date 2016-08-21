package com.kp.dto;

import com.kp.model.user.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.*;

public class ExampleUserDetails extends SocialUser {

	private Long id;

	private String avatarUrl;

	private String firstName;

	private String lastName;

	private Collection<Role> roles;

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public ExampleUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.append("username", getUsername())
				.append("firstName", firstName)
				.append("lastName", lastName)
				.append("roles", roles)
				.toString();
	}

	public static class Builder {

		private Long id;

		private String username;

		private String avatarUrl;

		private String firstName;

		private String lastName;

		private String password;

		private Collection<Role> roles;

		private Set<GrantedAuthority> authorities;

		public Builder() {
			this.authorities = new HashSet<GrantedAuthority>();
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder avatarUrl(String avatarUrl){
			this.avatarUrl=avatarUrl;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public Builder roles(Collection<Role> roles) {
			this.roles = roles;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.toString());
			this.authorities.add(authority);

			return this;
		}


		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public ExampleUserDetails build() {
			ExampleUserDetails user = new ExampleUserDetails(username, password, authorities);

			user.id = id;
			user.avatarUrl=avatarUrl;
			user.firstName = firstName;
			user.lastName = lastName;
			user.roles = roles;

			return user;
		}
	}
}
