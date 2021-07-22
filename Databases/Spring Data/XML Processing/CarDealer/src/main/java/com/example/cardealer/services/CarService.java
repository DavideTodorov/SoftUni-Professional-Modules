package com.example.cardealer.services;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.dtos.CarMakeModelAndDistanceDto;
import com.example.cardealer.entities.dtos.inputDtos.CarInputDto;
import com.example.cardealer.entities.dtos.inputDtos.CarWithPartsDto;

import java.util.List;

public interface CarService {

    void seedData(List<CarInputDto> cars);

    Car getRandomCar();

    List<CarMakeModelAndDistanceDto> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    List<CarWithPartsDto> findAllCarWithParts();
}
