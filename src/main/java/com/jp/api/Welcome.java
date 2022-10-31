package com.jp.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.security.model.repo.RoleRepo;
import com.jp.security.model.repo.UserRepo;
import com.jp.security.model.ERole;
import com.jp.security.model.Role;

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

}
