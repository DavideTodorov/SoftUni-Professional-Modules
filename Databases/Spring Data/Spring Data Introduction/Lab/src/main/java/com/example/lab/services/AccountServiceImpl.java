package com.example.lab.services;

import com.example.lab.models.Account;
import com.example.lab.repositories.AccountRepository;
import com.example.lab.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("account doesnt exist"));


        if (acc.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("not enough money to withdraw");
        }

        acc.setBalance(acc.getBalance().subtract(amount));
        accountRepository.save(acc);
    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {

        Account acc = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("account doesnt exist"));

        acc.setBalance(acc.getBalance().add(amount));
        accountRepository.save(acc);
    }
}
