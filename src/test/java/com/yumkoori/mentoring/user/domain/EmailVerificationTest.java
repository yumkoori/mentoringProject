package com.yumkoori.mentoring.user.domain;

import static org.assertj.core.api.Assertions.*;

import com.yumkoori.mentoring.user.domain.EmailVerification.verificationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailVerificationTest {

    private String email;

    @BeforeEach
    public void setUp() {
        email = "test20000402";
    }

    @DisplayName("인증 객체가 생성될때 인증 코드가 생성되어야 한다.")
    @Test
    void createVerificationCode() {
        //given
        EmailVerification verification = new EmailVerification(email);

        //when
        String getVerification = verification.getVerification();

        //then
        assertThat(getVerification).isNotEmpty();
    }

    @DisplayName("인증 객체가 생성될때 상태는 PENDING이어야 한다.")
    @Test
    void createVerificationStatus() {
        //given
        EmailVerification verification = new EmailVerification(email);

        //when
        verificationStatus status = verification.getStatus();

        //then
        assertThat(status).isEqualTo(verificationStatus.PENDING);

    }

    @DisplayName("입력된 인증코드가 일치하면 true, 아니면 false를 반환해야 한다.")
    @Test
    void isTrueVerification() {
        //given
        EmailVerification verification = new EmailVerification(email);
        String correctCode = verification.getVerification();
        String wrongCode = "0000";

        //then
        assertThat(verification.isTrueVerification(correctCode)).isTrue();
        assertThat(verification.isTrueVerification(wrongCode)).isFalse();

    }



}