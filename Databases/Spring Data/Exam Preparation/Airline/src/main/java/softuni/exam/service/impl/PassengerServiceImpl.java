package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PassengerInputDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final String PASSENGERS_FILE_PATH = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final TownService townService;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TownService townService, Gson gson,
                                ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.passengerRepository = passengerRepository;
        this.townService = townService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files
                .readString(Path.of(PASSENGERS_FILE_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        String passengersInputStr = readPassengersFileContent();
        StringBuilder sb = new StringBuilder();

        PassengerInputDto[] passengerInputDtos = gson.fromJson(passengersInputStr, PassengerInputDto[].class);

        List<String> emails = new ArrayList<>();

        List<Passenger> passengers = Arrays.stream(passengerInputDtos)
                .filter(passengerInputDto -> {
                    boolean valid = validationUtil.isValid(passengerInputDto);

                    if (emails.contains(passengerInputDto.getEmail()) ||
                            townService.findTownByName(passengerInputDto.getTown()) == null) {
                        valid = false;
                    } else {
                        emails.add(passengerInputDto.getEmail());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Passenger %s - %s",
                                passengerInputDto.getLastName(), passengerInputDto.getEmail()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Passenger")
                                .append(System.lineSeparator());
                    }


                    return valid;
                })
                .map(passengerInputDto -> {
                    Passenger passenger = modelMapper.map(passengerInputDto, Passenger.class);
                    passenger.setTown(townService.findTownByName(passengerInputDto.getTown()));
                    return passenger;
                })
                .collect(Collectors.toList());

        passengerRepository.saveAll(passengers);

        return sb.toString().trim();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        List<Passenger> passengers =
                passengerRepository.findAllAndOrderByTicketsCountDescAndEmailAsc();

        StringBuilder sb = new StringBuilder();

        passengers
                .forEach(p -> {
                    sb.append(String.format("Passenger %s %s\n" +
                                    "\tEmail - %s\n" +
                                    "\tPhone - %s\n" +
                                    "\tNumber of tickets - %d",
                            p.getFirstName(), p.getLastName(), p.getEmail(),
                            p.getPhoneNumber(), p.getTickets().size()))
                            .append(System.lineSeparator());
                });


        return sb.toString().trim();
    }

    @Override
    public Passenger findByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }
}
