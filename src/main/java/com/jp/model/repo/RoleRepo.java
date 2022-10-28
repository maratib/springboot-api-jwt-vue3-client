package com.jp.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.model.ERole;
import com.jp.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}