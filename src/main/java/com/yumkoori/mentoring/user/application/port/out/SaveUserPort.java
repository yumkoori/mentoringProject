package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.domain.User;

public interface SaveUserPort {

    void saveUser(User user);
}
