package com.yumkoori.mentoring.user.application.port.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yumkoori.mentoring.common.MentoringErrorCode;
import com.yumkoori.mentoring.user.application.port.in.command.CheckVerificationCommand;
import com.yumkoori.mentoring.user.application.port.in.command.RequestVerificationCommand;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import com.yumkoori.mentoring.user.application.port.out.LoadVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.SaveEmailVerificationPort;
import com.yumkoori.mentoring.user.application.port.out.UpdateVerificationPort;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import jakarta.validation.constraints.Email;
import java.util.Optional;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerificationServiceTest {

    private String email;
    private String code;

    @BeforeEach
    public void setUp() {
        email = "test20000402@gmail.com";
        code = "9999";
    }

    @InjectMocks
    private VerificationService verificationService;

    @Mock
    private SaveEmailVerificationPort saveEmailVerificationPort;

    @Mock
    private LoadVerificationPort loadVerificationPort;

    @Mock
    private UpdateVerificationPort updateVerificationPort;

    @Mock
    private LoadUserPort loadUserPort;

    @DisplayName("가입된적 없는 이메일로 인증코드 요청시 인증코드가 생성되고, 저장된다.")
    @Test
    void createVerification_Success() {
        //given
        String uniqueEmail = email;
        RequestVerificationCommand command = new RequestVerificationCommand(uniqueEmail);

        when(loadUserPort.existsByUserEmail(command.getEmail())).thenReturn(false);

        //when
        EmailVerification createdVerification = verificationService.createEmailVerification(command);

        //then
        assertThat(createdVerification.getVerification()).isNotEmpty();
        verify(saveEmailVerificationPort, times(1)).saveVerification(createdVerification);

    }

    @DisplayName("이미 가입된 이메일로 인증코드 요청시 EMAIL_DUPLICATION 예외가 발생한다.")
    @Test
    void createVerification_ThrowException_EmailDuplicate() {
        //given
        String existEmail = email;
        RequestVerificationCommand command = new RequestVerificationCommand(existEmail);

        when(loadUserPort.existsByUserEmail(command.getEmail())).thenReturn(true);

        //when
        UserServiceException exception = catchThrowableOfType(
                () -> verificationService.createEmailVerification(command),
                UserServiceException.class);

        //then
        assertThat(exception.errorType().getMessage()).isEqualTo(MentoringErrorCode.EMAIL_DUPLICATION.getMessage());
        assertThat(exception.errorType().getCode()).isEqualTo(MentoringErrorCode.EMAIL_DUPLICATION.getCode());


    }


    @DisplayName("인증코드가 발급되지 않은 이메일로 인증코드 확인시 VERIFICATION_NOT_EXIST 예외가 발생한다.")
    @Test
    void checkVerification_ThrowException_NotExist() {
        //given
        String notExistEmail = email;
        CheckVerificationCommand command = new CheckVerificationCommand(notExistEmail, code);

        when(loadVerificationPort.getEmailVerification(command.getEmail())).thenReturn(Optional.empty());

        //when
        VerificationException exception = catchThrowableOfType(() ->
                        verificationService.checkEmailVerification(command), VerificationException.class);

        //then
        assertThat(exception.getMessage()).isEqualTo(MentoringErrorCode.VERIFICATION_NOT_EXIST.getMessage());
        assertThat(exception.getErrorType().getCode()).isEqualTo(MentoringErrorCode.VERIFICATION_NOT_EXIST.getCode());

    }

    @DisplayName("인증 코드가 일치하지 않으면 false를 반환한다.")
    @Test
    void checkVerification_Fail() {
        //given
        String wrongCode = "9999";
        String correctCode = "1111";

        //잘못된 코드 입력
        CheckVerificationCommand command = new CheckVerificationCommand(wrongCode, email);
        //저장된 코드
        EmailVerification savedVerification = new EmailVerification(command.getEmail(), correctCode);

        when(loadVerificationPort.getEmailVerification(command.getEmail())).thenReturn(Optional.of(savedVerification));

        //when
        boolean checkResult = verificationService.checkEmailVerification(command);

        //then
        assertThat(checkResult).isFalse();

    }

    @DisplayName("인증 코드가 일치하면 상태가 VERIFIED로 변경되고, true를 반환한다.")
    @Test
    void checkVerification_Success() {
        //given
        String correctCode = code;
        CheckVerificationCommand command = new CheckVerificationCommand(email, correctCode);
        EmailVerification savedVerification = new EmailVerification(command.getEmail(), command.getVerificationCode());

        when(loadVerificationPort.getEmailVerification(command.getEmail())).thenReturn(Optional.of(savedVerification));

        //when
        boolean checkResult = verificationService.checkEmailVerification(command);

        //then
        verify(updateVerificationPort, times(1)).setVerifiedStatus(savedVerification);
        assertThat(checkResult).isTrue();
    }

}