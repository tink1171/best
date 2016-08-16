package com.kp.model.model_of_user;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

/*Created by diman on 11.08.16.*/

@Component
public class LoggedUser implements HttpSessionBindingListener {

	private String username;
	private ActiveUserStore activeUserStore;

	public LoggedUser() {
	}

	public LoggedUser(String username, ActiveUserStore activeUserStore) {
		this.username = username;
		this.activeUserStore=activeUserStore;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(activeUserStore);
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (users.contains(user.getUsername())) {
			users.remove(user.getUsername());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
