package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;

    public LoginResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
