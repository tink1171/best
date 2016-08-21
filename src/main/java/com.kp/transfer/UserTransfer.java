package com.kp.transfer;

import java.util.Map;

public class UserTransfer {

	private long id;

	private String avatarUrl;

	private String name;

	private Map<String, Boolean> roles;

	public UserTransfer() {
	}

	public UserTransfer(String userName){
		this.name = userName;
	}

	public UserTransfer(long id, String avatarUrl, String userName) {
		this.id=id;
		this.avatarUrl=avatarUrl;
		this.name = userName;
	}

	public String getName()
	{
		return this.name;
	}

	public Map<String, Boolean> getRoles()
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