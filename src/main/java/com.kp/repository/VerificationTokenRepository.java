package com.kp.repository;

import com.kp.model.model_of_user.User;
import com.kp.model.verification_token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by diman on 08.08.16.
 */
public interface VerificationTokenRepository extends JpaRepository <VerificationToken, Long> {
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User user);
}
