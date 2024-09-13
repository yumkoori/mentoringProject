package com.yumkoori.mentoring.user.application.port.in.command;

import com.yumkoori.mentoring.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserCommand extends SelfValidating<RegisterUserCommand> {

    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final String nick;

    public RegisterUserCommand(String email, String password, String nick) {
        this.email = email;
        this.password = password;
        this.nick = nick;

        this.validateSelf();
    }
}
