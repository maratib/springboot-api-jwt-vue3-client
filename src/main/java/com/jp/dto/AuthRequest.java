package com.jp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthRequest {

    @NotNull(message = "user is required")
    @Size(max = 255)
    private String user;

    @NotNull(message = "password is required")
    @Size(max = 255)
    private String password;

}
