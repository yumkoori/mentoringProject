package com.yumkoori.mentoring.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailVerification {

    private final String email;
    private final String verification;

    public EmailVerification(String email) {
        this.email = email;
        this.verification = this.createVerification();
    }

    private String createVerification() {
        String verification = "";

        for (int count = 0; count < 4; count++) {
            verification += (int) (Math.random() * 10);
        }

        return verification;
    }



}
