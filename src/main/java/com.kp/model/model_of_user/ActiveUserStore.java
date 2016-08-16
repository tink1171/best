package com.kp.model.model_of_user;

import java.util.ArrayList;
import java.util.List;

/*Created by diman on 11.08.16.*/


public class ActiveUserStore {
	public List<String> users;

	public ActiveUserStore() {
		users = new ArrayList<String>();
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
