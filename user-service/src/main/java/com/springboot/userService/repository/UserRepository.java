package com.springboot.userService.repository;

import com.springboot.userService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Users> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);
}
