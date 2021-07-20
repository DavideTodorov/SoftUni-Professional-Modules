package com.example.cardealer.services;

import com.example.cardealer.entities.Car;
import com.example.cardealer.entities.dtos.CarFullInfoDto;
import com.example.cardealer.entities.dtos.CarFullinfoWithPartsDto;
import com.example.cardealer.entities.dtos.CarInputDto;
import com.example.cardealer.repositories.CarRepository;
import com.example.cardealer.utils.ValidationUtil;
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
public class CarServiceImpl implements CarService {
    private static final String CAR_FILE_PATH = "src/main/resources/cars.json";

    private final CarRepository carRepository;
    private final PartService partService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, PartService partService, ModelMapper modelMapper,
                          ValidationUtil validationUtil, Gson gson) {
        this.carRepository = carRepository;
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }


    @Override
    public void seedData() throws IOException {
        if (carRepository.count() > 0) {
            return;
        }

        String inputString = Files.readString(Path.of(CAR_FILE_PATH));

        List<CarInputDto> collect = Arrays.stream(gson.fromJson(inputString, CarInputDto[].class))
                .collect(Collectors.toList());

        collect
                .forEach(carInputDto -> carInputDto.setParts(partService.getRandomParts()));

        List<Car> cars = collect
                .stream()
                .filter(validationUtil::isValid)
                .map(carInputDto -> modelMapper.map(carInputDto, Car.class))
                .collect(Collectors.toList());

        carRepository.saveAll(cars);
    }

    @Override
    public Car getRandomCar() {
        return carRepository
                .findById(ThreadLocalRandom.current().nextLong(1, carRepository.count()) + 1)
                .orElse(null);
    }

    @Override
    public List<CarFullInfoDto> getCarsByMake(String make) {
        List<Car> allByMakeOrderByModelAscTravelledDistanceAsc =
                carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);

        return allByMakeOrderByModelAscTravelledDistanceAsc
                .stream()
                .map(c -> modelMapper.map(c, CarFullInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarFullinfoWithPartsDto> getAllCarsWithParts() {
        List<Car> allCars = carRepository.findAll();

        return allCars
                .stream()
                .map(c -> modelMapper.map(c, CarFullinfoWithPartsDto.class))
                .collect(Collectors.toList());
    }
}
