package com.jp.security.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.security.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    // Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    // Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}