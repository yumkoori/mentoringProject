package com.yumkoori.mentoring.user.application.port.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yumkoori.mentoring.user.application.port.in.command.RegisterUserCommand;
import com.yumkoori.mentoring.user.application.port.out.LoadVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.PasswordEncoderPort;
import com.yumkoori.mentoring.user.application.port.out.SaveUserPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import com.yumkoori.mentoring.user.domain.EmailVerification.verificationStatus;
import com.yumkoori.mentoring.user.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


@ExtendWith(MockitoExtension.class)
class SignUpUserServiceTest {

    @Mock
    private LoadVerificationPort loadVerificationPort;
    @Mock
    private SaveUserPort saveUserPort;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignUpUserService signUpUserService;

    @DisplayName("인증 상태가 'VERIFIED'인 User는 회원가입에 성공한다.")
    @Test
    void SignUp_Success_WhenVERIFIED() {
        //given
        String email = "test@gmail.com";
        String verificationCode = "9999";
        String nick = "test";
        String password = "1111";

        EmailVerification verification = EmailVerification.builder()
                .email(email)
                .verification(verificationCode)
                .status(verificationStatus.VERIFIED)
                .build();

        RegisterUserCommand command = RegisterUserCommand.builder()
                .email(email)
                .nick(nick)
                .password(password)
                .build();

        when(loadVerificationPort.getEmailVerification(email)).thenReturn(Optional.of(verification));
        when(passwordEncoderPort.passwordEncoder()).thenReturn(passwordEncoder);

        //when

        signUpUserService.registerUser(command);

        //then
        verify(saveUserPort, times(1)).saveUser(Mockito.any(User.class));

    }

}