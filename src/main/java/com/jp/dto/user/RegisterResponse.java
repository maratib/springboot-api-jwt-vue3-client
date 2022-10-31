package com.jp.dto.user;

import java.util.Date;

import lombok.Data;

@Data
public class RegisterResponse {

    private Integer id;
    private String email;
    private Date created_at;

    public RegisterResponse(Integer id, String email, Date created_at) {
        this.id = id;
        this.email = email;
        this.created_at = created_at;
    }

}
