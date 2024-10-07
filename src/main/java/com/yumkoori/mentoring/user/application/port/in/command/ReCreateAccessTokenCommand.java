package com.yumkoori.mentoring.user.application.port.in.command;

import com.yumkoori.mentoring.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReCreateAccessTokenCommand extends SelfValidating<ReCreateAccessTokenCommand> {

    @NotBlank
    private final String refreshToken;

    public ReCreateAccessTokenCommand(String refreshToken) {
        this.refreshToken = refreshToken;

        this.validateSelf();
    }
}
