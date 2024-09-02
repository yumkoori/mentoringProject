package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CheckVerificationDto {

    private String email;
    private String verificationCode;

}
