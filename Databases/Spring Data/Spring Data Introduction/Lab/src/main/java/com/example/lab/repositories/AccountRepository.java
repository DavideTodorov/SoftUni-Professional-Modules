package com.example.lab.repositories;

import com.example.lab.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountById(Long id);

    boolean existsAccountById(Long id);
}
