package com.yumkoori.mentoring.user.application.port.in.command;


import com.yumkoori.mentoring.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestVerficationCommand extends SelfValidating<RequestVerficationCommand> {

    @NotBlank
    @Email
    private final String email;

    public RequestVerficationCommand(String email) {
        this.email = email;

        this.validateSelf();
    }
}
