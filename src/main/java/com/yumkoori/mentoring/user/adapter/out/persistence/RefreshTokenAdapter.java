package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.common.PersistenceAdapter;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.RefreshTokenJpaEntity;
import com.yumkoori.mentoring.user.application.port.out.LoadRefreshTokenPort;
import com.yumkoori.mentoring.user.application.port.out.SaveRefreshTokenPort;
import com.yumkoori.mentoring.user.domain.RefreshToken;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RefreshTokenAdapter implements LoadRefreshTokenPort, SaveRefreshTokenPort {

    private final RefreshTokenRepository repository;


    @Override
    public RefreshToken getRefreshToken(String refreshToken) {
        RefreshTokenJpaEntity refreshTokenJpaEntity = repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));

        return RefreshToken.builder()
                .id(refreshTokenJpaEntity.getId())
                .userId(refreshTokenJpaEntity.getUserId())
                .refreshToken(refreshTokenJpaEntity.getRefreshToken())
                .build();
    }

    @Override
    public void saveRefreshToken(Long userId, String refreshToken) {
        repository.save(new RefreshTokenJpaEntity(userId, refreshToken));
    }


}
