package com.yumkoori.mentoring.user.adapter.jwt;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.yumkoori.mentoring.user.adapter.out.persistence.SpringDataUserRepository;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.UserJpaEntity;
import com.yumkoori.mentoring.user.adapter.out.persistence.mapper.PersistenceUserMapper;
import com.yumkoori.mentoring.user.domain.CustomUserDetails;
import com.yumkoori.mentoring.user.domain.User;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private SpringDataUserRepository repository;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달하면 토큰을 생성할 수 있다.")
    @Test
    void generateToken() {
        //given
        UserJpaEntity userJpaEntity = repository.save(UserJpaEntity.builder()
                .email("test@gmail.com")
                .password("test")
                .build());

        User testUser = User.builder()
                .email(userJpaEntity.getEmail())
                .password(userJpaEntity.getPassword())
                .build();
        //when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));
        //then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("validToken(): 만료된 토큰일 경우에는 유효성 검증에 실패한다.")
    @Test
    void validToken_invalidToken() {
        //given
        //만료된 토큰 생성
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validToken(token);
        //then
        assertThat(result).isFalse();
    }

    @DisplayName("validToken(): 유효한 토큰이라면 유효성 검증에 성공한다.")
    @Test
    void validToken_validToken() {
        //given
        String token =JwtFactory.withDefaultValues().createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validToken(token);
        //then
        assertThat(result).isTrue();
    }

    @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        //given
        String userEmail = "test@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);
        //when
        Authentication authentication = tokenProvider.getAuthentication(token);
        //then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    @DisplayName("getUserId(): 토큰으로 유저 Id를 가져올 수 있다.")
    @Test
    void getUserId() {
        //given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);
        //when
        Long userIdByToken = tokenProvider.getUserId(token);
        //then
        assertThat(userIdByToken).isEqualTo(userId);

    }


}