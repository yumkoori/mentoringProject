package com.yumkoori.mentoring.user.application.port.out;

public interface LoadUserPort {

    boolean existsByUserEmail(String email);
}
