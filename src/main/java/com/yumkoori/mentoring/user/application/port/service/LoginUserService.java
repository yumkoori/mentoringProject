package com.yumkoori.mentoring.user.application.port.service;

import com.yumkoori.mentoring.common.UseCase;
import com.yumkoori.mentoring.user.application.port.in.LoginUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.LoginCommand;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import com.yumkoori.mentoring.user.application.port.out.PasswordEncoderPort;
import com.yumkoori.mentoring.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class LoginUserService implements LoginUseCase {

    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public User authenticate(LoginCommand loginCommand) {
        User user = loadUserPort.findByEmail(loginCommand.getEmail());

        System.out.println(user.getPassword());
        System.out.println(loginCommand.getPassword());

        if (passwordEncoderPort.passwordEncoder().matches(loginCommand.getPassword(),
                user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Not Matches Password");
        }
    }

}
