package com.yumkoori.mentoring.user.application.port.out;

public interface SaveRefreshTokenPort {
    void saveRefreshToken(Long userId, String refreshToken);
}
