package com.yumkoori.mentoring.user.application.port.out;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderPort {
    PasswordEncoder passwordEncoder();
}
