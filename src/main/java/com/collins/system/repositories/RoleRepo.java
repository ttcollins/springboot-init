package com.collins.system.repositories;

import com.collins.system.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ttc
 * @version 1.0
 */
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
