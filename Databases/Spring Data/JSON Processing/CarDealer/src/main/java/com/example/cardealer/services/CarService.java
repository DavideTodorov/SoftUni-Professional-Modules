package com.example.cardealer.services;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.dtos.CarFullInfoDto;
import com.example.cardealer.entities.dtos.CarFullinfoWithPartsDto;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedData() throws IOException;

    Car getRandomCar();

    List<CarFullInfoDto> getCarsByMake(String make);

    List<CarFullinfoWithPartsDto> getAllCarsWithParts();
}
