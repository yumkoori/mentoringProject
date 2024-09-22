package com.yumkoori.mentoring.user.application.port.in.command;


import com.yumkoori.mentoring.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestVerificationCommand extends SelfValidating<RequestVerificationCommand> {

    @NotBlank
    @Email
    private final String email;

    public RequestVerificationCommand(String email) {
        this.email = email;

        this.validateSelf();
    }
}
