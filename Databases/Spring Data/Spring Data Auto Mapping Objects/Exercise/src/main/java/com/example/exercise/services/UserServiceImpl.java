package com.example.exercise.services;

import com.example.exercise.entities.Game;
import com.example.exercise.entities.User;
import com.example.exercise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private User loggedUser;
    private final GameService gameService;

    public UserServiceImpl(UserRepository userRepository, GameService gameService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
    }

    @Override
    public String registerUser(String email, String password, String confirmPassword, String fullName) {
        //Check if email exists
        if (userRepository.findByEmail(email) != null) {
            return "User with this email is already registered!";
        }

        //Check if email matches the criteria
        if (!email.contains("@") || !email.contains(".")) {
            return "Incorrect email!";
        }

        //Check if password matches the criteria
        Pattern passwordValidationPattern = Pattern.compile("^[A-Z]+[a-z]+[\\d]+$");
        Matcher matcher = passwordValidationPattern.matcher(password);
        if (!matcher.find() || password.length() < 6) {
            return "Invalid password: " + password;
        }

        //Check if confirmPassword matches password
        if (password.compareTo(confirmPassword) != 0) {
            return "Confirm password doesn't match!";
        }

        User user = new User(email, password, fullName);
        userRepository.save(user);

        return String.format("%s was registered!", fullName.split("\\s+")[0]);
    }

    @Override
    public String loginUser(String email, String password) {
        //Check if email exists
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "User with this email was not found!";
        }

        if (user.getPassword().compareTo(password) != 0) {
            return "Incorrect password!";
        }

        this.loggedUser = user;

        return String.format("Successfully logged in %s", user.getFullName().split("\\s+")[0]);
    }

    @Override
    public String logout() {
        if (this.loggedUser == null) {
            return "Cannot log out. No user was logged in.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Logged out user: ").append(this.loggedUser.getFullName()).append(System.lineSeparator());

        this.loggedUser = null;

        return sb.toString().trim();
    }

    @Transactional
    @Override
    public String purchaseGame(String gameTitle) {
        if (loggedUser == null) {
            return "No user logged";
        }

        Game byTitle = gameService.findByTitle(gameTitle);

        if (byTitle == null) {
            return "Game doesn't exist";
        }

        loggedUser.purchase(byTitle);
        userRepository.save(loggedUser);

        return loggedUser.getFullName() + " purchased " + byTitle.getTitle();
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
