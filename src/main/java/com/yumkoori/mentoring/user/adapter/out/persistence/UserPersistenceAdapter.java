package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.common.PersistenceAdapter;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort {

    private final SpringDataUserRepository repository;

    @Override
    public boolean existsByUserEmail(String email) {
        return repository.existsByEmail(email);
    }

}
