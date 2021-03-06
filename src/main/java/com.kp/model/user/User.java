package com.kp.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kp.model.comment.Comment;
import com.kp.model.site.Site;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by diman on 05.08.16.
 */
@Entity
@Table(name = "user_account")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="avatar_url")
	private String avatarUrl;


	@Column(name = "firstname")

	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name ="email")
	private String email;

	@Column(length = 60, name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@JsonBackReference
	private Collection<Role> roles;

	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinTable(name="user_site",
			joinColumns={@JoinColumn(name="user_id")},
			inverseJoinColumns={@JoinColumn(name="site_id")})
	private List<Site> site;
//
//	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
//	@JoinTable(name="user_comment",
//			joinColumns={@JoinColumn(name="user_id")},
//			inverseJoinColumns={@JoinColumn(name="comment_id")})
//	private List<Comment> comments;

	public User() {
		super();
		this.enabled = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String Email) {
		this.email = Email;
	}

	public String getPassword() {
		return password;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public List<Site> getSite() {
		return site;
	}

	public void setSite(List<Site> site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User user = (User) obj;
		if (!email.equals(user.email)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
						"id=" + id +
						", avatarUrl='" + avatarUrl + '\'' +
						", firstName='" + firstName + '\'' +
						", lastName='" + lastName + '\'' +
						", username='" + username + '\'' +
						", email='" + email + '\'' +
						", password='" + password + '\'' +
						", enabled=" + enabled +
						", roles=" + roles +
						'}';
	}
}