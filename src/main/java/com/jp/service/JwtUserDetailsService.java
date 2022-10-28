package com.jp.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;

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
        String password = passwordEncoder.encode("password");
        UserDetails user = new User("mail@mail.com", password, Collections.singleton(
                new SimpleGrantedAuthority(ROLE_USER)));
        System.out.println("User : " + user);
        return user;
    }

}