package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.common.PersistenceAdapter;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.EmailVerificationJpaEntity;
import com.yumkoori.mentoring.user.adapter.out.persistence.mapper.PersistenceVerifyMapper;
import com.yumkoori.mentoring.user.application.port.out.LoadVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.SaveEmailVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.UpdateVerificationPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import com.yumkoori.mentoring.user.domain.EmailVerification.verificationStatus;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmailVerificationPersistenceAdapter
        implements SaveEmailVerificationPort, UpdateVerificationPort, LoadVerificationPort {

    private final SpringDataEmailVerificationRepository repository;

    @Override
    public void saveVerification(EmailVerification emailVerification) {

        EmailVerificationJpaEntity emailVerificationJpaEntity =
                new EmailVerificationJpaEntity(emailVerification.getEmail(), emailVerification.getVerification(), emailVerification.getStatus());
        repository.save(emailVerificationJpaEntity);
    }

    @Override
    public void setVerifiedStatus(EmailVerification emailVerification) {

        EmailVerificationJpaEntity jpaEntity = repository.findById(emailVerification.getEmail()).orElseThrow();

        jpaEntity.setStatus(verificationStatus.VERIFIED);

    }

    @Override
    public Optional<EmailVerification> getEmailVerification(String email) {

        EmailVerificationJpaEntity findJpaEntity = repository.findById(email)
                .orElseThrow(()-> new NoSuchElementException("Not Found Verification"));

        return Optional.of(PersistenceVerifyMapper.mapToVerificationDomain(findJpaEntity));
    }
}
