package com.example.shopdatabase.services.impl;

import com.example.shopdatabase.entities.User;
import com.example.shopdatabase.entities.dtos.UserInputDto;
import com.example.shopdatabase.entities.dtos.UserWithSoldProductsDto;
import com.example.shopdatabase.repositories.UserRepository;
import com.example.shopdatabase.services.UserService;
import com.example.shopdatabase.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData(List<UserInputDto> userInputDtos) {
        if (userRepository.count() > 0) {
            return;
        }

        List<User> collect = userInputDtos
                .stream()
                .map(userInputDto -> modelMapper.map(userInputDto, User.class))
                .filter(validationUtil::isValid)
                .collect(Collectors.toList());

        userRepository.saveAll(collect);
    }

    @Override
    public User getRandomUser() {
        return userRepository
                .findById(ThreadLocalRandom
                        .current()
                        .nextLong(1, userRepository.count() + 1))
                .orElse(null);
    }

    @Override
    public List<UserWithSoldProductsDto> findAllByCountOfSalesMoreThanOne() {
        return userRepository.findAllByCountOfSalesMoreThanOne()
                .stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toList());
    }
}
