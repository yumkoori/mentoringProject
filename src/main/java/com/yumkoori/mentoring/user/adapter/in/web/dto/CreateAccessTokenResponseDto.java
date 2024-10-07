package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;

@Data
public class CreateAccessTokenResponseDto {

    private String accessToken;

    public CreateAccessTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
