package com.restaurant.repositories.jpa;

import com.restaurant.models.authority.Role;
import com.restaurant.models.authority.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJPARepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
