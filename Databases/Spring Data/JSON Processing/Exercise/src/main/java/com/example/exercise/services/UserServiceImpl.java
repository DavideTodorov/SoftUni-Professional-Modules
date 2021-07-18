package com.example.exercise.services;

import com.example.exercise.entities.User;
import com.example.exercise.entities.dtos.UserInputDto;
import com.example.exercise.entities.dtos.UserNamesAndProductsDto;
import com.example.exercise.repositories.UserRepository;
import com.example.exercise.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FILE_PATH = "src/main/resources/users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           Gson gson, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedData() throws IOException {
        if (userRepository.count() > 0) {
            return;
        }

        String s = Files.readString(Path.of(USER_FILE_PATH));
        UserInputDto[] userInputDtos = gson.fromJson(s, UserInputDto[].class);

        Arrays.stream(userInputDtos)
                .filter(validationUtil::isValid)
                .map(userInputDto -> modelMapper.map(userInputDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User findRandom() {
        long randId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository
                .findById((int) randId)
                .orElse(null);
    }

    @Override
    public List<UserNamesAndProductsDto> findAllWhoHaveSales() {

        return userRepository.findAllByCountOfSalesMoreThanOne()
                .stream()
                .map(u -> modelMapper.map(u, UserNamesAndProductsDto.class))
                .collect(Collectors.toList());
    }
}