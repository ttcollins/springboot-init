package com.collins.system.repositories;

import com.collins.system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ttc
 * @version 1.0
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
