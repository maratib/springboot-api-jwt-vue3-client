package com.jp.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jp.dto.user.RegisterRequest;
import com.jp.security.model.ERole;
import com.jp.security.model.Role;
import com.jp.security.model.User;
import com.jp.security.model.repo.RoleRepo;
import com.jp.security.model.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(RegisterRequest req) throws Exception {

        User newUser = new User(req.getEmail(), req.getPassword());

        Optional<Role> role = roleRepo.findById(req.getRole());
        if (role.isPresent()) {
            newUser.addRole(role.get());
        }

        return this.save(newUser);
    }

    private User save(User user) throws Exception {
        Optional<User> alreadyExists = userRepo.findByEmail(user.getEmail());

        if (alreadyExists.isPresent()) {
            throw new Exception("User already exists");
        }

        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        return userRepo.save(user);
    }

    public void initAdminUserCheck() throws Exception {
        Optional<User> user = userRepo.findByEmail("admin@admin.com");

        if (user.isEmpty()) {
            if (roleRepo.count() < 1) {
                roleRepo.save(new Role(ERole.ADMIN));
                roleRepo.save(new Role(ERole.EDITOR));
                roleRepo.save(new Role(ERole.USER));
            }

            Optional<Role> role = roleRepo.findByName(ERole.ADMIN);

            User newUser = new User("admin@admin.com", "admin");

            if (role.isPresent()) {
                newUser.addRole(role.get());
            }

            this.save(newUser);

            log.warn("Initial Admin User Created");
        }

    }
}