package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserServiceException extends RuntimeException{

    private final MentoringErrorCode errorCode;

    public UserServiceException(MentoringErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public MentoringErrorCode errorType() {
        return errorCode;
    }

}
