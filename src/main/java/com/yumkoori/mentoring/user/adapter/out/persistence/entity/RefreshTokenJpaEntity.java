package com.yumkoori.mentoring.user.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class RefreshTokenJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "refresh_token")
    private String refreshToken;

    public RefreshTokenJpaEntity(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public RefreshTokenJpaEntity update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        return this;
    }
}
