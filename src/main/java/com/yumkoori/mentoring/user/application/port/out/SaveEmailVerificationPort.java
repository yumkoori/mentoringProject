package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.domain.EmailVerification;

public interface SaveEmailVerificationPort {

    void saveVerification(EmailVerification emailVerification);
    
}
