package com.yumkoori.mentoring.user.application.port.in.command;

import com.yumkoori.mentoring.common.SelfValidating;
import com.yumkoori.mentoring.user.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateAccessTokenCommand extends SelfValidating<CreateAccessTokenCommand> {
    @NotNull
    private final User user;

    public CreateAccessTokenCommand(User user) {
        this.user = user;

        this.validateSelf();
    }
}
