package com.yumkoori.mentoring.user.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupRequestForEmailDto {

    private final String email;
    private final String password;
    private final String nick;

}
