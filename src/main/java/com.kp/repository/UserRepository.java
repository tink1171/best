package com.kp.repository;

import com.kp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by diman on 05.08.16.
 */

public interface UserRepository extends JpaRepository <User, Long> {
	User findByEmail(String Email);
	User findById(Long id);
	User findByUsername(String username);
	@Override
	void delete(User user);
}
