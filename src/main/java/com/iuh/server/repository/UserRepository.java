package com.iuh.server.repository;

import com.iuh.server.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Integer> {
    // find by email
    Optional<Account> findByEmail(String email);

    // exist email
    boolean existsByEmail(String email);
}
