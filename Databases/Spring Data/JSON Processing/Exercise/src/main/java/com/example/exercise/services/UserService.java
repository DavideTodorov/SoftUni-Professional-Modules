package com.example.exercise.services;

import com.example.exercise.entities.User;
import com.example.exercise.entities.dtos.UserNamesAndProductsDto;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedData() throws IOException;

    User findRandom();

    List<UserNamesAndProductsDto> findAllWhoHaveSales();
}
