package com.jp.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jp.model.ERole;
import com.jp.model.Role;
import com.jp.model.repo.RoleRepo;
import com.jp.model.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    public static final String ROLE_USER = "ROLE_USER";

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(final String username) {
        System.out.println("JwtUserDetailsService loadUserByName - " + username);
        // final Client client = clientRepository.findByLogin(username).orElseThrow(
        // () -> new UsernameNotFoundException("User " + username + " not found"));
        // return new User(username, client.getHash(), Collections.singletonList(new
        // SimpleGrantedAuthority(ROLE_USER)));
        // UserDetails user =
        // User.withUsername("mail@mail.com").password("password").authorities("USER").build();

        final UserDetails userDetails = userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));

        // String password = passwordEncoder.encode("password");
        // UserDetails user = new User("mail@mail.com", password, Collections.singleton(
        // new SimpleGrantedAuthority(ROLE_USER)));
        System.out.println("User : " + userDetails);
        return userDetails;
    }

    public void initAdminUserCheck() {
        Optional<com.jp.model.User> user = userRepo.findByUsername("admin");

        if (user.isEmpty()) {
            roleRepo.save(new Role(ERole.ROLE_ADMIN));
            roleRepo.save(new Role(ERole.ROLE_EDITOR));
            roleRepo.save(new Role(ERole.ROLE_USER));

            Set<Role> roles = new HashSet<>();
            Optional<Role> role = roleRepo.findByName(ERole.ROLE_ADMIN);
            if (role.isPresent()) {
                roles.add(role.get());
            }

            String encodedPassword = passwordEncoder.encode("admin");
            com.jp.model.User myUser = new com.jp.model.User("admin", "admin@admin.com", encodedPassword);

            myUser.setRoles(roles);

            userRepo.save(myUser);

            log.warn("Initial Admin User Created");
        }

    }

}