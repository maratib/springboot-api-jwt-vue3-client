package com.jp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Welcome {
    @GetMapping
    public String home() {
        log.warn("from Home");
        return "Hello, World!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello, User!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }
}
