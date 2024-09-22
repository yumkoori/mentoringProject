package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import com.yumkoori.mentoring.common.UseCase;
import com.yumkoori.mentoring.common.enums.AuthProvider;
import com.yumkoori.mentoring.common.enums.UserRole;
import com.yumkoori.mentoring.common.enums.UserStatus;
import com.yumkoori.mentoring.user.application.port.in.RegisterUserUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.RegisterUserCommand;
import com.yumkoori.mentoring.user.application.port.out.LoadVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.PasswordEncoderPort;
import com.yumkoori.mentoring.user.application.port.out.SaveUserPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import com.yumkoori.mentoring.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SignUpUserService implements RegisterUserUseCase {

    private final LoadVerificationPort loadVerificationPort;
    private final SaveUserPort saveUserPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public void registerUser(RegisterUserCommand registerUserCommand) {

        EmailVerification emailVerification = loadVerificationPort.getEmailVerification(
                registerUserCommand.getEmail()).orElseThrow(() ->
                new VerificationException(MentoringErrorCode.VERIFICATION_NOT_EXIST));

        if(emailVerification.isStatusVERIFIED()) {
            User user = User.builder()
                    .email(registerUserCommand.getEmail())
                    .password(passwordEncoderPort.passwordEncoder().encode(registerUserCommand.getPassword()))
                    .nick(registerUserCommand.getNick())
                    .role(UserRole.MENTEE)
                    .status(UserStatus.ACTIVE)
                    .provider(AuthProvider.EMAIL)
                    .build();

            log.info("encoderPw={}",user.getPassword());
            saveUserPort.saveUser(user);
        }
    }
}
