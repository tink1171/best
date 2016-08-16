package com.kp.repository;

import com.kp.model.model_of_user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by diman on 06.08.16.
 */

public interface RoleRepository extends JpaRepository <Role, Long> {
	Role findByName(String name);
}
