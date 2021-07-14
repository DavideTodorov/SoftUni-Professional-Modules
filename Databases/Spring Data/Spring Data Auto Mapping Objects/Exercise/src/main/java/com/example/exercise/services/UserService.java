package com.example.exercise.services;

import com.example.exercise.entities.User;

public interface UserService {

    String registerUser(String email, String password, String confirmPassword, String fullName);

    String loginUser(String email, String password);

    String logout();

    String purchaseGame(String gameTitle);

    User getLoggedUser();
}
