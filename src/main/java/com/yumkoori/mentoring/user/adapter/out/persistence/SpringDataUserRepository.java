package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.user.adapter.out.persistence.entity.UserJpaEntity;
import com.yumkoori.mentoring.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserJpaEntity> findByEmail(String email);
}
