package com.kp.security;

import com.kp.controllers.RegistrationController;
import com.kp.model.model_of_user.Role;
import com.kp.model.model_of_user.User;
import com.kp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by diman on 06.08.16.
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	private UserRepository userRepository;

//	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.debug(" load by with email " + email);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			LOGGER.debug("null");
			throw new UsernameNotFoundException("No user found with email: "+ email);
		}
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		LOGGER.debug("Role in userdetails " + user.getRoles().toString());
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
						user.getPassword().toLowerCase(),user.isEnabled(),
						accountNonExpired,credentialsNonExpired,
						accountNonLocked,getAuthorities(user.getRoles()));

	}

	public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(final Collection<Role> roles) {
		List<String> privileges = new ArrayList<String>();
		for(final Role role: roles){
			privileges.add(role.getName());
		}
		return privileges;
	}

	private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges){
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
