package com.jp.api.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.ApiApplication;

@RestController
@RequestMapping("api/user")
public class UserApi {

    @GetMapping
    public String home() {
        return "User API Version : " + ApiApplication.appVersion;

    }

}
