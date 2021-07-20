package com.example.cardealer.services;

import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.dtos.PartInputDto;
import com.example.cardealer.repositories.PartRepository;
import com.example.cardealer.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
    private static final String PART_FILE_PATH = "src/main/resources/parts.json";

    private final PartRepository partRepository;
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public PartServiceImpl(PartRepository partRepository, SupplierService supplierService,
                           ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.partRepository = partRepository;
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {
        if (partRepository.count() > 0) {
            return;
        }

        String dataString = Files.readString(Path.of(PART_FILE_PATH));

        List<PartInputDto> partInputDtos = Arrays.stream(gson.fromJson(dataString, PartInputDto[].class))
                .collect(Collectors.toList());

        for (PartInputDto partInputDto : partInputDtos) {
            Supplier random = supplierService.findRandom();
            partInputDto.setSupplier(random);
        }

        partInputDtos
                .stream()
                .filter(validationUtil::isValid)
                .map(partInputDto -> modelMapper.map(partInputDto, Part.class))
                .forEach(partRepository::save);
    }

    @Override
    public Set<Part> getRandomParts() {
        int countOfParts = ThreadLocalRandom.current().nextInt(3, 6);

        Set<Part> parts = new HashSet<>();

        int i = 0;
        while (i < countOfParts) {
            Part part = partRepository.findById(ThreadLocalRandom
                    .current()
                    .nextLong(1, partRepository.count() + 1))
                    .orElse(null);

            if (parts.contains(part)) {
                continue;
            }

            parts.add(part);
            i++;
        }

        return parts;
    }
}
