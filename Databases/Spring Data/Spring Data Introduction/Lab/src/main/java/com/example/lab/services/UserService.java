package com.example.lab.services;

import com.example.lab.models.Account;
import com.example.lab.models.User;

public interface UserService {
    void registerUser(User user) throws IllegalStateException;

    void addAccount(Account account, User user);
}
