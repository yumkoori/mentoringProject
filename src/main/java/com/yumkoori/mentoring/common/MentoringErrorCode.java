package com.yumkoori.mentoring.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MentoringErrorCode {

    //10xx Common

    //11xx User
    EMAIL_DUPLICATION(1100, "이미 존재하는 이메일입니다.");


    private final Integer code;
    private final String message;

}
