package com.yumkoori.mentoring.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class EmailVerification {

    private final String email;
    private final String verification;
    private verificationStatus status;

    public EmailVerification(String email) {
        this.email = email;
        this.status = verificationStatus.PENDING;
        this.verification = this.createVerification();
    }

    public EmailVerification(String email, String verification, verificationStatus status) {
        this.email = email;
        this.verification = verification;
        this.status = status;
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

    public boolean isStatusVERIFIED() {
        return this.status.equals(verificationStatus.VERIFIED);
    }

    public enum verificationStatus {
        PENDING,
        VERIFIED,
        EXPIRED
    }



}
