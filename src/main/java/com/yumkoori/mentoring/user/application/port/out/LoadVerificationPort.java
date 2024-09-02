package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.domain.EmailVerification;
import java.util.Optional;

public interface LoadVerificationPort {

    Optional<EmailVerification> getEmailVerification(String email);
}
