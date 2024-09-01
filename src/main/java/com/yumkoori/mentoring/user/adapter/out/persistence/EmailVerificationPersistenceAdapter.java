package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.common.PersistenceAdapter;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.EmailVerificationJpaEntity;
import com.yumkoori.mentoring.user.application.port.out.SaveEmailVerificationPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmailVerificationPersistenceAdapter implements SaveEmailVerificationPort {

    private final SpringDataEmailVerificationRepository repository;

    @Override
    public void saveVerification(EmailVerification emailVerification) {

        EmailVerificationJpaEntity emailVerificationJpaEntity =
                new EmailVerificationJpaEntity(emailVerification.getEmail(), emailVerification.getVerifyCation());
        repository.save(emailVerificationJpaEntity);
    }



}
