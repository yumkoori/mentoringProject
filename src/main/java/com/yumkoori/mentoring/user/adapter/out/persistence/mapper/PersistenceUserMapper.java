package com.yumkoori.mentoring.user.adapter.out.persistence.mapper;

import com.yumkoori.mentoring.common.enums.AuthProvider;
import com.yumkoori.mentoring.common.enums.UserRole;
import com.yumkoori.mentoring.common.enums.UserStatus;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.UserJpaEntity;
import com.yumkoori.mentoring.user.domain.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
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

    public static User mapToUser(UserJpaEntity jpaEntity) {
        return User.builder()
                .email(jpaEntity.getEmail())
                .password(jpaEntity.getPassword())
                .nick(jpaEntity.getNick())
                .role(jpaEntity.getRole())
                .status(jpaEntity.getUserStatus())
                .provider(jpaEntity.getProvider())
                .build();
    }

}
