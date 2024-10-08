package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;
}
