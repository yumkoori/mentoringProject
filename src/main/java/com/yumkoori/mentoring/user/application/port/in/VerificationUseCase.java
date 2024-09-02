package com.yumkoori.mentoring.user.application.port.in;

import com.yumkoori.mentoring.user.application.port.in.command.CheckVerificationCommand;
import com.yumkoori.mentoring.user.application.port.in.command.RequestVerificationCommand;
import com.yumkoori.mentoring.user.domain.EmailVerification;

public interface VerificationUseCase {

    EmailVerification requestEmailVerification(RequestVerificationCommand RequestCommand);

    boolean checkEmailVerification(CheckVerificationCommand verificationCommand);

}
