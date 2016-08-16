package com.kp.repository;

import com.kp.model.model_of_user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by diman on 05.08.16.
 */

public interface UserRepository extends JpaRepository <User, Long> {
	User findByEmail(String email);

	@Override
	void delete(User user);
}
