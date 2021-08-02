package com.example.football.service.impl;

import com.example.football.models.dto.BestPlayerOutputDto;
import com.example.football.models.dto.PlayerInputRootDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;

    public PlayerServiceImpl(PlayerRepository playerRepository, XmlParser xmlParser,
                             ModelMapper modelMapper, ValidationUtil validationUtil,
                             TownService townService, TeamService teamService, StatService statService) {
        this.playerRepository = playerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files
                .readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        PlayerInputRootDto playerInputRootDto = xmlParser.fromFile(PLAYERS_FILE_PATH, PlayerInputRootDto.class);

        StringBuilder sb = new StringBuilder();
        List<String> emails = new ArrayList<>();

        List<Player> players = playerInputRootDto
                .getPlayers()
                .stream()
                .filter(playerInputDto -> {
                    boolean valid = validationUtil.isValid(playerInputDto);

                    if (emails.contains(playerInputDto.getEmail())) {
                        valid = false;
                    } else {
                        emails.add(playerInputDto.getEmail());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Player %s %s - %s",
                                playerInputDto.getFirstName(), playerInputDto.getLastName(),
                                playerInputDto.getPosition().name()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Player")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(playerInputDto -> {
                    Player player = modelMapper.map(playerInputDto, Player.class);

                    player.setTown(townService.findByName(playerInputDto.getTownName().getName()));
                    player.setTeam(teamService.findByName(playerInputDto.getTeamName().getName()));
                    player.setStat(statService.findById(playerInputDto.getStatId().getId()));

                    return player;
                })
                .collect(Collectors.toList());

        playerRepository.saveAll(players);

        return sb.toString().trim();
    }

    @Override
    public String exportBestPlayers() {
        LocalDate startDate = LocalDate.parse("1995-01-01");
        LocalDate endDate = LocalDate.parse("2003-01-01");

        List<BestPlayerOutputDto> bestPlayers =
                playerRepository.findAllByBirthDateBetweenAndOrderByStatsDesc(startDate, endDate);

        StringBuilder sb = new StringBuilder();

        bestPlayers
                .forEach(player -> sb.append(String.format("Player - %s %s\n" +
                                "\tPosition - %s\n" +
                                "\tTeam - %s\n" +
                                "\tStadium - %s",
                        player.getFirstName(), player.getLastName(), player.getPosition().name(),
                        player.getTeamName(), player.getStadiumName()))
                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
