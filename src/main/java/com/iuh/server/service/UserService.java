package com.iuh.server.service;

import com.iuh.server.model.entity.Account;

import java.util.Optional;

public interface UserService {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    Account save(Account entity);
}
