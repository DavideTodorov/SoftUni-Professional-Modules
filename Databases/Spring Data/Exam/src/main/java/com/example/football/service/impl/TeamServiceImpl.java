package com.example.football.service.impl;

import com.example.football.models.dto.TeamInputDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String TEAMS_FILE_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TownService townService;

    public TeamServiceImpl(TeamRepository teamRepository, Gson gson,
                           ModelMapper modelMapper, ValidationUtil validationUtil,
                           TownService townService) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files
                .readString(Path.of(TEAMS_FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        String teamsFileContent = readTeamsFileContent();

        TeamInputDto[] teamInputDtos = gson.fromJson(teamsFileContent, TeamInputDto[].class);

        StringBuilder sb = new StringBuilder();
        List<String> names = new ArrayList<>();

        List<Team> teams = Arrays.stream(teamInputDtos)
                .filter(teamInputDto -> {
                    boolean valid = validationUtil.isValid(teamInputDto);

                    if (names.contains(teamInputDto.getName())) {
                        valid = false;
                    } else {
                        names.add(teamInputDto.getName());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Team %s - %d",
                                teamInputDto.getName(), teamInputDto.getFanBase()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Team")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(teamInputDto -> {
                    Team team = modelMapper.map(teamInputDto, Team.class);
                    team.setTown(townService.findByName(teamInputDto.getTownName()));
                    return team;
                })
                .collect(Collectors.toList());

        teamRepository.saveAll(teams);

        return sb.toString().trim();
    }

    @Override
    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }
}
