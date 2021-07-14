package com.example.exercise.services;

import com.example.exercise.entities.Game;
import com.example.exercise.entities.User;

import java.math.BigDecimal;

public interface GameService {
    String addGame(String title, String priceInput, String sizeInput, String trailer,
                   String thumbnailURL, String description, String releaseDate);

    String editGame(int id, String priceToken, String sizeToken);

    String deleteGame(int id);

    String printAllGames();

    String printDetailGame(String title);

    Game findByTitle(String title);

    String printOwnedGames(User loggedUser);
}
