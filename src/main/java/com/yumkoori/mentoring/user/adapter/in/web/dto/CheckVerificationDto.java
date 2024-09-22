package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;

@Data
public class CheckVerificationDto {

    private String email;
    private String verificationCode;

}
