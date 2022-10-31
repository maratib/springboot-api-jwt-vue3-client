package com.jp.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AuthRequest {

    @NotNull(message = "email is required")
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @NotNull(message = "password is required")
    @Length(min = 5, max = 20)
    private String password;

}
