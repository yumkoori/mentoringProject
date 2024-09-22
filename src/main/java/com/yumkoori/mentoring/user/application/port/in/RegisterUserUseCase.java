package com.yumkoori.mentoring.user.application.port.in;

import com.yumkoori.mentoring.user.application.port.in.command.RegisterUserCommand;

public interface RegisterUserUseCase {

    void registerUser(RegisterUserCommand registerUserCommand);

}
