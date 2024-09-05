package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.user.adapter.out.persistence.entity.EmailVerificationJpaEntity;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class PersistenceMapper {

    public static EmailVerificationJpaEntity mapToVerificationJpaEntity(EmailVerification verificationDomain) {
        return new EmailVerificationJpaEntity(verificationDomain.getEmail(),
                verificationDomain.getVerification(), verificationDomain.getStatus());
    }

    public static EmailVerification mapToVerificationDomain(EmailVerificationJpaEntity verificationJpaEntity) {
        return new EmailVerification(verificationJpaEntity.getEmail());
    }
}
