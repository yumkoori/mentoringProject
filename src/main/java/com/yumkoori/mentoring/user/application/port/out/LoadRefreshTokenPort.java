package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.domain.RefreshToken;
import com.yumkoori.mentoring.user.domain.User;

public interface LoadRefreshTokenPort {

    RefreshToken getRefreshToken(String refreshToken);
    void saveRefreshToken(Long userId, String refreshToken);
}
