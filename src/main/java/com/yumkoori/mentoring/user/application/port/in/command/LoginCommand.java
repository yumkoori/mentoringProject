package com.yumkoori.mentoring.user.application.port.in.command;

import com.yumkoori.mentoring.common.SelfValidating;
import lombok.Getter;

@Getter
public class LoginCommand extends SelfValidating<LoginCommand> {

    private final String email;
    private final String password;

    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }
}
