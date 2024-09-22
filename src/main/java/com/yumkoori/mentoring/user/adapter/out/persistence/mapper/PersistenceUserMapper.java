package com.yumkoori.mentoring.user.adapter.out.persistence.mapper;

import com.yumkoori.mentoring.user.adapter.out.persistence.entity.UserJpaEntity;
import com.yumkoori.mentoring.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PersistenceUserMapper {

    public static UserJpaEntity mapToUserJpaEntity(User user) {
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .nick(user.getNick())
                .role(user.getRole())
                .userStatus(user.getStatus())
                .provider(user.getProvider())
                .build();
    }

}
