package com.example.shopdatabase.services;

import com.example.shopdatabase.entities.User;
import com.example.shopdatabase.entities.dtos.UserInputDto;
import com.example.shopdatabase.entities.dtos.UserWithSoldProductsDto;

import java.util.List;

public interface UserService {

    void seedData(List<UserInputDto> userInputDtos);

    User getRandomUser();

    List<UserWithSoldProductsDto> findAllByCountOfSalesMoreThanOne();
}
