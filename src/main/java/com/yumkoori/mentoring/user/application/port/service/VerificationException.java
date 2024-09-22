package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import lombok.Getter;

public class VerificationException extends RuntimeException{

    private final MentoringErrorCode errorCode;

    public VerificationException(MentoringErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public MentoringErrorCode getErrorType() {
        return errorCode;
    }
}
