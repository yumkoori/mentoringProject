package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EmailVerificationRequestDto {

    private String email;

}
