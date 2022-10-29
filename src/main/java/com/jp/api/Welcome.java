package com.jp.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.model.ERole;
import com.jp.model.Role;
import com.jp.model.repo.RoleRepo;
import com.jp.model.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Welcome {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @GetMapping
    public List<Role> home() {

        log.warn("from Home");
        // return "Hello, World!";

        return roleRepo.findAll();

    }

    @GetMapping("/user")
    @RolesAllowed({ ERole.ROLE_ADMIN, ERole.ROLE_EDITOR })
    public String user() {
        return "Hello, User!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }
}
