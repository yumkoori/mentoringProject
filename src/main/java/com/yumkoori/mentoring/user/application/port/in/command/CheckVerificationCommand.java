package com.yumkoori.mentoring.user.application.port.in.command;

import com.yumkoori.mentoring.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CheckVerificationCommand extends SelfValidating<CheckVerificationCommand> {

    @NotBlank
    private final String email;

    @NotBlank
    private final String verificationCode;

    public CheckVerificationCommand(String verificationCode, String email) {
        this.verificationCode = verificationCode;
        this.email = email;
        this.validateSelf();
    }

}
