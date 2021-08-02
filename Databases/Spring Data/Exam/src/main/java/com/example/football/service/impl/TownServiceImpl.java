package com.example.football.service.impl;

import com.example.football.models.dto.TownInputDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
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
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, Gson gson,
                           ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files
                .readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        String townsFileContent = readTownsFileContent();

        TownInputDto[] townInputDtos = gson.fromJson(townsFileContent, TownInputDto[].class);
        StringBuilder sb = new StringBuilder();

        List<String> names = new ArrayList<>();

        List<Town> towns = Arrays.stream(townInputDtos)
                .filter(townInputDto -> {
                    boolean valid = validationUtil.isValid(townInputDto);

                    if (names.contains(townInputDto.getName())) {
                        valid = false;
                    } else {
                        names.add(townInputDto.getName());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Town %s - %d",
                                townInputDto.getName(), townInputDto.getPopulation()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Town")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(townInputDto -> modelMapper.map(townInputDto, Town.class))
                .collect(Collectors.toList());

        townRepository.saveAll(towns);

        return sb.toString().trim();
    }

    @Override
    public Town findByName(String name) {
        return townRepository.findByName(name);
    }
}
