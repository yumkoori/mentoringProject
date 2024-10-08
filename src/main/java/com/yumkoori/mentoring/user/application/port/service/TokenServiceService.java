package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.UseCase;
import com.yumkoori.mentoring.user.application.port.in.TokenServiceUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.CreateAccessTokenCommand;
import com.yumkoori.mentoring.user.application.port.in.command.ReCreateAccessTokenCommand;
import com.yumkoori.mentoring.user.application.port.out.LoadRefreshTokenPort;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import com.yumkoori.mentoring.user.application.port.out.SaveRefreshTokenPort;
import com.yumkoori.mentoring.user.application.port.out.TokenProviderPort;
import com.yumkoori.mentoring.user.domain.User;
import java.time.Duration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class TokenServiceService implements TokenServiceUseCase {
    private final TokenProviderPort tokenProviderPort;
    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final LoadUserPort loadUserPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;

    //AccessToken 유효시간 만료 시 재발급
    public String ReCreateNewAccessToken(ReCreateAccessTokenCommand reCreateAccessTokenCommand) {
        if(!tokenProviderPort.validToken(reCreateAccessTokenCommand.getRefreshToken())) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = loadRefreshTokenPort.getRefreshToken(reCreateAccessTokenCommand.getRefreshToken()).getUserId();
        User user = loadUserPort.getByUserId(userId);

        return tokenProviderPort.generateToken(user, Duration.ofHours(1));
    }
    //AccessToken 로그인 시 최초 발급
    public String createNewAccessToken(CreateAccessTokenCommand createAccessTokenCommand) {
        return tokenProviderPort
                .generateToken(createAccessTokenCommand.getUser(), Duration.ofHours(1));
    }

    //RefreshToken 로그인 시 최초 발급
    public String createNewRefreshToken(CreateAccessTokenCommand createAccessTokenCommand) {
        //토큰 발급
        String refreshToken = tokenProviderPort.generateToken(
                createAccessTokenCommand.getUser(),
                Duration.ofHours(10));
        //토큰 저장
        saveRefreshTokenPort.saveRefreshToken(
                createAccessTokenCommand.getUser().getId(),
                refreshToken);

        return refreshToken;
    }

}
