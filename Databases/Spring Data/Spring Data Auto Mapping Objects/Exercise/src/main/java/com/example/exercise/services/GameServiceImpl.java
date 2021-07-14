package com.example.exercise.services;

import com.example.exercise.entities.Game;
import com.example.exercise.entities.User;
import com.example.exercise.entities.dtos.GameDetailsDto;
import com.example.exercise.entities.dtos.GamePriceAndTitleDto;
import com.example.exercise.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public String addGame(String title, String priceInput, String sizeInput, String trailer, String thumbnailURL,
                          String description, String releaseDate) {
        //Validate title
        if (Character.isLowerCase(title.charAt(0)) || title.length() < 3 || title.length() > 100) {
            return "Invalid title";
        }

        //Validate price
        BigDecimal price = new BigDecimal(priceInput);
        if (price.compareTo(new BigDecimal(0)) <= 0) {
            return "Price cannot be negative!";
        }

        //Validate size
        BigDecimal size = new BigDecimal(priceInput);
        if (size.compareTo(new BigDecimal(0)) <= 0) {
            return "Size cannot be negative!";
        }

        //Validate trailer
        if (trailer.length() != 11) {
            return "only videos from YouTube are allowed!";
        }

        //Validate thumbnailURl
        if (thumbnailURL.startsWith("http://") || thumbnailURL.startsWith("https://")) {
        } else {
            return "Invalid thumbnailURl";
        }

        //Validate Description
        if (description.length() < 20) {
            return "Invalid description";
        }

        String[] dateTokens = releaseDate.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(dateTokens[2]),
                Integer.parseInt(dateTokens[1]),
                Integer.parseInt(dateTokens[0]));

        Game game = new Game(title, trailer, thumbnailURL, size, price, description, date);
        gameRepository.save(game);

        return "Added " + title;
    }

    @Override
    public String editGame(int id, String priceToken, String sizeToken) {
        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            return "Invalid Id!";
        }

        BigDecimal price = new BigDecimal(priceToken.split("=")[1]);
        BigDecimal size = new BigDecimal(sizeToken.split("=")[1]);

        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);

        return "Edited " + game.getTitle();
    }

    @Override
    public String deleteGame(int id) {
        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            return "Invalid Id!";
        }

        gameRepository.delete(game);

        return "Deleted " + game.getTitle();
    }

    @Override
    public String printAllGames() {
        StringBuilder sb = new StringBuilder();
        List<Game> all = gameRepository.findAll();

        ModelMapper mapper = new ModelMapper();

        List<GamePriceAndTitleDto> dtos =
                all.stream().map(game -> mapper.map(game, GamePriceAndTitleDto.class)).collect(Collectors.toList());

        for (GamePriceAndTitleDto dto : dtos) {
            sb.append(dto).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String printDetailGame(String title) {
        Game byTitle = gameRepository.findByTitle(title);
        ModelMapper mapper = new ModelMapper();

        mapper.addMappings(new PropertyMap<Game, GameDetailsDto>() {
            @Override
            protected void configure() {
                map().setInfo(source.getDescription());
                map().setDate(source.getReleaseDate());
            }
        });

        GameDetailsDto gameDetailsDto = mapper.map(byTitle, GameDetailsDto.class);

        return gameDetailsDto.toString();
    }

    @Override
    public Game findByTitle(String title) {
        return gameRepository.findByTitle(title);
    }

    @Override
    public String printOwnedGames(User loggedUser) {
        StringBuilder sb = new StringBuilder();

        if (loggedUser == null) {
            System.out.println("No logged user!");
        }

        for (Game game : loggedUser.getGames()) {
            sb.append(game.getTitle()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
