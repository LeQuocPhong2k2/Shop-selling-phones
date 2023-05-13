package com.iuh.server.service.impl;

import com.iuh.server.model.entity.Account;
import com.iuh.server.repository.UserRepository;
import com.iuh.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Account save(Account entity) {
        return repository.save(entity);
    }
}
