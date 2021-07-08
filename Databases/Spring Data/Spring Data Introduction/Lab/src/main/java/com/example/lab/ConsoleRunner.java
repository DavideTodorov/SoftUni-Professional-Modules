package com.example.lab;

import com.example.lab.models.Account;
import com.example.lab.models.User;
import com.example.lab.services.AccountService;
import com.example.lab.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        accountService.transferMoney(new BigDecimal(3300), 1L);
    }
}
