package com.yumkoori.mentoring.user.application.port.in;

import com.yumkoori.mentoring.user.application.port.in.command.RequestVerficationCommand;
import com.yumkoori.mentoring.user.domain.EmailVerification;

public interface VerifyEmailUseCase {

    EmailVerification requestEmailVerification(RequestVerficationCommand RequestCommand);
    boolean checkEmailVerification(RequestVerficationCommand verificationCommand);

}
