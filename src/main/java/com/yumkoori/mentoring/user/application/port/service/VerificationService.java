package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import com.yumkoori.mentoring.common.UseCase;
import com.yumkoori.mentoring.user.application.port.in.VerifyEmailUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.RequestVerficationCommand;
import com.yumkoori.mentoring.user.application.port.out.SaveEmailVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VerificationService implements VerifyEmailUseCase {

    private final LoadUserPort loadUserPort;
    private final SaveEmailVerificationPort saveEmailVerificationPort;

    @Override
    public EmailVerification requestEmailVerification(RequestVerficationCommand command) {


        //1. email 중복 검증
        if (loadUserPort.existsByUserEmail(command.getEmail())) {
            throw new UserServiceException(MentoringErrorCode.EMAIL_DUPLICATION);
        }

        log.info("중복 검증 성공");
        //2. 인증 코드 생성
        EmailVerification emailVerification = new EmailVerification(command.getEmail());
        log.info("인증번호 생성 성공 = {}", emailVerification.getVerifyCation());

        //3.이메일 인증서 저장
        saveEmailVerificationPort.saveVerification(emailVerification);
        log.info("인증 저장 성공 ");

        return emailVerification;


    }


    @Override
    public boolean checkEmailVerification(RequestVerficationCommand verificationCommand) {
        return false;
    }
}
