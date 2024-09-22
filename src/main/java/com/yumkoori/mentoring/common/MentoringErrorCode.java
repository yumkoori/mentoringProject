package com.yumkoori.mentoring.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MentoringErrorCode {

    //10xx Common

    //11xx User
    EMAIL_DUPLICATION(1100, "이미 존재하는 이메일입니다."),

    //12xx Verification
    VERIFICATION_NOT_EXIST(1200, "메일 인증 정보가 존재하지 않습니다.");

    private final Integer code;
    private final String message;

}
