package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.adapter.out.persistence.entity.UserJpaEntity;
import com.yumkoori.mentoring.user.domain.CustomUserDetails;
import com.yumkoori.mentoring.user.domain.User;
import java.util.Optional;

public interface LoadUserPort {

    User getByUserId(Long userId);
    boolean existsByUserEmail(String email);
    User findByEmail(String email);
}
