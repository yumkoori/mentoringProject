package com.yumkoori.mentoring.user.domain;

import com.yumkoori.mentoring.common.enums.AuthProvider;
import com.yumkoori.mentoring.common.enums.UserRole;
import com.yumkoori.mentoring.common.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;


@RequiredArgsConstructor
@Builder
@Getter
public class User {
    private final String email;
    private final String password;
    private final String nick;
    private final UserRole role;
    private final UserStatus status;
    private final AuthProvider provider;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private final ZonedDateTime deletedAt;

    //닉네임 변경
    //역할 변경

}

