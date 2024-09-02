package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import com.yumkoori.mentoring.common.UseCase;
import com.yumkoori.mentoring.user.application.port.in.VerificationUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.CheckVerificationCommand;
import com.yumkoori.mentoring.user.application.port.in.command.RequestVerificationCommand;
import com.yumkoori.mentoring.user.application.port.out.LoadVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.SaveEmailVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VerificationService implements VerificationUseCase{

    private final LoadUserPort loadUserPort;
    private final SaveEmailVerificationPort saveEmailVerificationPort;
    private final LoadVerificationPort loadVerificationPort;

    @Override
    public EmailVerification requestEmailVerification(RequestVerificationCommand command) {


        //1. email 중복 검증
        if (loadUserPort.existsByUserEmail(command.getEmail())) {
            throw new UserServiceException(MentoringErrorCode.EMAIL_DUPLICATION);
        }

        log.info("중복 검증 성공");
        //2. 인증 코드 생성
        EmailVerification emailVerification = new EmailVerification(command.getEmail());
        log.info("인증번호 생성 성공 = {}", emailVerification.getVerification());

        //3.이메일 인증서 저장
        saveEmailVerificationPort.saveVerification(emailVerification);
        log.info("인증 저장 성공 ");

        return emailVerification;
    }


    @Override
    public boolean checkEmailVerification(CheckVerificationCommand verificationCommand) {

        EmailVerification emailVerification = loadVerificationPort.getEmailVerification(
                verificationCommand.getEmail()).orElseThrow(() ->
                new VerificationException(MentoringErrorCode.VERIFICATION_NOT_EXIST));

        if(!emailVerification.isTrueVerification(verificationCommand.getVerificationCode())) {
            return false;
        }

        return true;
    }
}
