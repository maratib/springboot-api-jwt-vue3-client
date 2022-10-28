package com.jp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthRequest {

    @NotNull(message = "login is required")
    @Size(max = 255)
    private String login;

    @NotNull(message = "password is required")
    @Size(max = 255)
    private String password;

}
