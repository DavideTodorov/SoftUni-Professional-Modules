package com.example.cardealer.services;

import com.example.cardealer.entities.Customer;
import com.example.cardealer.entities.dtos.CustomerWithSpentMoneyDto;
import com.example.cardealer.entities.dtos.inputDtos.CustomerInputDto;
import com.example.cardealer.entities.dtos.CustomerWithNameAndAgeDto;

import java.util.List;

public interface CustomerService {

    void seedData(List<CustomerInputDto> customers);

    Customer getRandomCustomer();

    List<CustomerWithNameAndAgeDto> findAllAndOrderByBirthdateAndIsYoungDriver();

    List<CustomerWithSpentMoneyDto> findAllByCarsBoughtMoreThanOne();
}
