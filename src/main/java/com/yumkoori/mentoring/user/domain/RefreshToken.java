package com.yumkoori.mentoring.user.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshToken {

    private Long id;
    private Long userId;
    private String refreshToken;
}
