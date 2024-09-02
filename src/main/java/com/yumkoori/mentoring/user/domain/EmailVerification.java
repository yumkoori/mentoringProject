package com.yumkoori.mentoring.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailVerification {

    private final String email;
    private final String verification;
    private final verificationStatus status;

    public EmailVerification(String email, verificationStatus status) {
        this.email = email;
        this.status = status;
        this.verification = this.createVerification();
    }

    private String createVerification() {
        String verification = "";

        for (int count = 0; count < 4; count++) {
            verification += (int) (Math.random() * 10);
        }

        return verification;
    }

    public boolean isTrueVerification(String requestVerification) {
        return requestVerification.equals(this.verification);
    }

    public enum verificationStatus {
        PENDING,
        VERIFIED,
        EXPIRED
    }

}
