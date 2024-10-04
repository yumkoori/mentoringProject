package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.RefreshTokenJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenJpaEntity, Long> {

    Optional<RefreshTokenJpaEntity> findByUserId(Long userId);
    Optional<RefreshTokenJpaEntity> findByRefreshToken(String refreshToken);
}
