package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.dtos.CarInputDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String CARS_FILE_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, Gson gson,
                          ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files
                .readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        String carsInputString = readCarsFileContent();

        CarInputDto[] carInputDtos = gson.fromJson(carsInputString, CarInputDto[].class);

        StringBuilder sb = new StringBuilder();

        List<Car> cars = Arrays.stream(carInputDtos)
                .filter(carInputDto -> {
                    boolean valid = validationUtil.isValid(carInputDto);

                    if (valid) {
                        sb.append(String.format("Successfully imported car - %s - %s",
                                carInputDto.getMake(), carInputDto.getModel()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid car")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(carInputDto -> modelMapper.map(carInputDto, Car.class))
                .collect(Collectors.toList());

        carRepository.saveAll(cars);

        return sb.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        carRepository.findAllAndOrderByPicturesCountDescAndMakeAsc()
                .forEach(c -> sb.append(String.format("Car make - %s, model - %s\n" +
                                "\tKilometers - %d\n" +
                                "\tRegistered on - %s\n" +
                                "\tNumber of pictures - %d",
                        c.getMake(), c.getModel(), c.getKilometers(), c.getRegisteredOn().toString(),
                        c.getPictures().size()))
                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }

    @Override
    public Car findCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }
}
