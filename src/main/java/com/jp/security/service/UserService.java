package com.jp.security.service;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User save(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        return userRepo.save(user);
    }

    public void initAdminUserCheck() {
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