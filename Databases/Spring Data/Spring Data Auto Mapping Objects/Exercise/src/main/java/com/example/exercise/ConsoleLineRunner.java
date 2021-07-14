package com.example.exercise;

import com.example.exercise.services.GameService;
import com.example.exercise.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleLineRunner implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;

    public ConsoleLineRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose action (If you want to end the operation enter End): ");

        String[] tokens = scanner.nextLine().split("\\|");

        while (!tokens[0].equalsIgnoreCase("End")) {
            if (tokens[0].equals("RegisterUser")) {
                System.out.println(userService.registerUser(tokens[1], tokens[2], tokens[3], tokens[4]));
            } else if (tokens[0].equals("LoginUser")) {
                System.out.println(userService.loginUser(tokens[1], tokens[2]));
            } else if (tokens[0].equals("Logout")) {
                System.out.println(userService.logout());
            } else if (tokens[0].equals("Purchase")) {
                System.out.println(userService.purchaseGame(tokens[1]));
            } else if (tokens[0].equals("AddGame")) {
                System.out.println(
                        gameService.addGame(tokens[1], tokens[2], tokens[3],
                                tokens[4], tokens[5], tokens[6], tokens[7]));
            } else if (tokens[0].equals("EditGame")) {
                System.out.println(gameService.editGame(Integer.parseInt(tokens[1]), tokens[2], tokens[3]));
            } else if (tokens[0].equals("DeleteGame")) {
                System.out.println(gameService.deleteGame(Integer.parseInt(tokens[1])));
            } else if (tokens[0].equals("AllGames")) {
                System.out.println(gameService.printAllGames());
            } else if (tokens[0].equals("DetailGame")) {
                System.out.println(gameService.printDetailGame(tokens[1]));
            } else if (tokens[0].equals("OwnedGames")) {
                System.out.println(gameService.printOwnedGames(userService.getLoggedUser()));
            }

            System.out.println("Choose action (If you want to end the operation enter End): ");
            tokens = scanner.nextLine().split("\\|");
        }
    }
}
