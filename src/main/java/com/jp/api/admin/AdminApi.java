package com.jp.api.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.ApiApplication;
import com.jp.dto.user.RegisterRequest;
import com.jp.dto.user.RegisterResponse;
import com.jp.exception.ErrorResponse;
import com.jp.security.model.User;
import com.jp.security.service.UserService;

@RestController
@RequestMapping("api/admin")
public class AdminApi {

    @Autowired
    UserService userService;

    @GetMapping
    public String home() {
        return "Admin API Version: " + ApiApplication.appVersion;
    }

    @PostMapping("create-user")
    public ResponseEntity<?> CreateUser(@RequestBody @Valid RegisterRequest req) throws Exception {

        User user = userService.createUser(req);
        RegisterResponse res = null;
        if (user != null) {
            res = new RegisterResponse(user.getId(), user.getEmail(), user.getCreated());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }

}
