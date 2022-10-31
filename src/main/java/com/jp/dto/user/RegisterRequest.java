package com.jp.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull(message = "email is required")
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @NotNull(message = "password is required")
    @Length(min = 5, max = 20, message = "password length must be between 5 to 20")
    private String password;

    @NotNull(message = "role is required")
    @Min(value = 1, message = "role must be greater than or equal to 1")
    @Max(value = 3, message = "role must be less than or equal to 3")
    private Integer role;

}
