package com.springboot.userService.repository;

import com.springboot.userService.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
