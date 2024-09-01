package com.yumkoori.mentoring.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailVerification {

    private final String email;
    private final String verifyCation;

    public EmailVerification(String email) {
        this.email = email;
        this.verifyCation = this.createVerification();
    }

    private String createVerification() {
        String verifyCation = "";

        for (int count = 0; count < 4; count++) {
            verifyCation += (int) (Math.random() * 10);
        }

        return verifyCation;
    }

}
