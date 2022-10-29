package com.jp.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    // @Override
    // public UserDetails loadUserByUsername(final String username) {
    // System.out.println("JwtUserDetailsService loadUserByName - " + username);
    // // final Client client = clientRepository.findByLogin(username).orElseThrow(
    // // () -> new UsernameNotFoundException("User " + username + " not found"));
    // // return new User(username, client.getHash(), Collections.singletonList(new
    // // SimpleGrantedAuthority(ROLE_USER)));
    // // UserDetails user =
    // //
    // User.withUsername("mail@mail.com").password("password").authorities("USER").build();

    // final UserDetails userDetails =
    // userRepo.findByUsername(username).orElseThrow(
    // () -> new UsernameNotFoundException("User " + username + " not found"));

    // // String password = passwordEncoder.encode("password");
    // // UserDetails user = new User("mail@mail.com", password,
    // Collections.singleton(
    // // new SimpleGrantedAuthority(ROLE_USER)));
    // System.out.println("User : " + userDetails);
    // return userDetails;
    // }

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