package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;

@Data
public class CreateAccessTokenRequestDto {
    private String refreshToken;
}
