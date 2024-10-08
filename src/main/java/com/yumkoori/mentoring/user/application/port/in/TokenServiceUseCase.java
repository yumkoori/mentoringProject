package com.yumkoori.mentoring.user.application.port.in;

import com.yumkoori.mentoring.user.application.port.in.command.CreateAccessTokenCommand;
import com.yumkoori.mentoring.user.application.port.in.command.ReCreateAccessTokenCommand;

public interface TokenServiceUseCase {

    String ReCreateNewAccessToken(ReCreateAccessTokenCommand refreshToken);
    String createNewAccessToken(CreateAccessTokenCommand createAccessTokenCommand);
    String createNewRefreshToken(CreateAccessTokenCommand createAccessTokenCommand);
}
