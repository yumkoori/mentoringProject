package com.yumkoori.mentoring.user.application.port.in;

import com.yumkoori.mentoring.user.application.port.in.command.LoginCommand;
import com.yumkoori.mentoring.user.domain.User;

public interface LoginUseCase {

    User authenticate(LoginCommand loginCommand);

}
