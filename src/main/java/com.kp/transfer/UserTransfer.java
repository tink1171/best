package com.kp.transfer;

import com.kp.model.user.Role;

import java.util.Collection;
import java.util.Map;

public class UserTransfer {

	private long id;

	private String avatarUrl;

	private String name;

	private Collection<Role> roles;

	public UserTransfer() {
	}

	public UserTransfer(String userName){
		this.name = userName;
	}

	public UserTransfer(long id, String avatarUrl, String userName, Collection<Role> roles) {
		this.id=id;
		this.avatarUrl=avatarUrl;
		this.name = userName;
		this.roles = roles;
	}

	public String getName()
	{
		return this.name;
	}

	public Collection<Role> getRoles()
	{
		return this.roles;
	}

	public long getId() {
		return id;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

}