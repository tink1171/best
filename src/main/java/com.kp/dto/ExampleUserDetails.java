package com.kp.dto;

import com.kp.model.user.Role;
import com.kp.model.user.SocialMediaService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by diman on 15.08.16.
 */
public class ExampleUserDetails extends SocialUser {

	private Long id;

	private String avatarUrl;

	private String firstName;

	private String lastName;

	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	private SocialMediaService socialSignInProvider;

	public ExampleUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		roles = new HashSet<Role>();
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

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}


	public static class Builder {

		private Long id;

		private String username;

		private String avatarUrl;

		private String firstName;

		private String lastName;

		private String password;

		private Set<Role> roles;

		private SocialMediaService socialSignInProvider;

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

		public Builder roles(Set<Role> roles) {
			this.roles = roles;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.toString());
			this.authorities.add(authority);

			return this;
		}

		public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
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
			user.socialSignInProvider = socialSignInProvider;

			return user;
		}
	}
}