package com.kp.model.user;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

/*Created by diman on 11.08.16.*/

@Component
public class LoggedUser implements HttpSessionBindingListener {

	private String Email;
	private ActiveUserStore activeUserStore;

	public LoggedUser() {
	}

	public LoggedUser(String Email, ActiveUserStore activeUserStore) {
		this.Email = Email;
		this.activeUserStore=activeUserStore;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(activeUserStore);
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (!users.contains(user.getEmail())) {
			users.add(user.getEmail());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (users.contains(user.getEmail())) {
			users.remove(user.getEmail());
		}
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
}
