package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.user.adapter.out.persistence.entity.EmailVerificationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEmailVerificationRepository extends JpaRepository<EmailVerificationJpaEntity, String> {

}
