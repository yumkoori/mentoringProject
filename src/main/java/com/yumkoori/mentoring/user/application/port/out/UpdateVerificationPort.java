package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.domain.EmailVerification;

public interface UpdateVerificationPort {

    void setVerifiedStatus(EmailVerification emailVerification);
}
