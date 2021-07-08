package com.example.lab.services;

import com.example.lab.models.Account;
import com.example.lab.models.User;
import com.example.lab.repositories.AccountRepository;
import com.example.lab.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(User user) throws IllegalStateException {

        if (userRepository.existsUsersByUsername(user.getUsername())) {
            throw new IllegalStateException("user exists");
        }

        userRepository.save(user);
    }

    @Override
    public void addAccount(Account account, User user) {
        if (!userRepository.existsUsersByUsername(user.getUsername())) {
            throw new IllegalStateException("user dosent exists");
        }

        User userFound = userRepository.findUserByUsername(user.getUsername());

        User byId = userRepository.findById(userFound.getId()).orElseThrow(IllegalStateException::new);
        account.setUser(byId);
        accountRepository.save(account);

    }
}
