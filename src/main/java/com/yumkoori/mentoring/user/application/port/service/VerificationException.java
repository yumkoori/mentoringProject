package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import lombok.Getter;

@Getter
public class VerificationException extends RuntimeException{

    private final MentoringErrorCode mentoringErrorCode;

    public VerificationException(MentoringErrorCode mentoringErrorCode) {
        this.mentoringErrorCode = mentoringErrorCode;
    }
}
