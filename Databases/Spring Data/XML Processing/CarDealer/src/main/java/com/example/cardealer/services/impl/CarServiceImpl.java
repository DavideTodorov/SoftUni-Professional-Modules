package com.example.cardealer.services.impl;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.dtos.CarMakeModelAndDistanceDto;
import com.example.cardealer.entities.dtos.inputDtos.CarInputDto;
import com.example.cardealer.entities.dtos.inputDtos.CarWithPartsDto;
import com.example.cardealer.repositories.CarRepository;
import com.example.cardealer.services.CarService;
import com.example.cardealer.services.PartService;
import com.example.cardealer.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper,
                          ValidationUtil validationUtil, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.partService = partService;
    }

    @Override
    public void seedData(List<CarInputDto> cars) {
        if (carRepository.count() > 0) {
            return;
        }

        List<Car> collect = cars
                .stream()
                .filter(validationUtil::isValid)
                .map(carInputDto -> {
                    Car map = modelMapper.map(carInputDto, Car.class);
                    map.setParts(partService.getRandomParts());
                    return map;
                })
                .collect(Collectors.toList());

        carRepository.saveAll(collect);
    }

    @Override
    public Car getRandomCar() {
        return carRepository
                .findById(
                        ThreadLocalRandom
                                .current()
                                .nextLong(1, carRepository.count() + 1))
                .orElse(null);
    }

    @Override
    public List<CarMakeModelAndDistanceDto> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make) {
        return carRepository
                .findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(car -> modelMapper.map(car, CarMakeModelAndDistanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarWithPartsDto> findAllCarWithParts() {
        return carRepository
                .findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarWithPartsDto.class))
                .collect(Collectors.toList());
    }
}
