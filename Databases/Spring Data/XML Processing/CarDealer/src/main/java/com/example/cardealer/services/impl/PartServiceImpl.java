package com.example.cardealer.services.impl;

import com.example.cardealer.entities.Part;
import com.example.cardealer.entities.dtos.inputDtos.PartInputDto;
import com.example.cardealer.repositories.PartRepository;
import com.example.cardealer.services.PartService;
import com.example.cardealer.services.SupplierService;
import com.example.cardealer.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper,
                           ValidationUtil validationUtil, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.supplierService = supplierService;
    }


    @Override
    public void seedData(List<PartInputDto> parts) {
        if (partRepository.count() > 0) {
            return;
        }

        List<Part> collect = parts
                .stream()
                .filter(validationUtil::isValid)
                .map(partInputDto -> {
                    Part map = modelMapper.map(partInputDto, Part.class);
                    map.setSupplier(supplierService.getRandomSupplier());
                    return map;
                })
                .collect(Collectors.toList());

        partRepository.saveAll(collect);
    }

    @Override
    public Set<Part> getRandomParts() {
        int randCount = ThreadLocalRandom.current().nextInt(10, 21);

        long partsCount = partRepository.count();

        Set<Part> parts = new HashSet<>();

        for (int i = 0; i < randCount; i++) {
            parts.add(
                    partRepository
                            .findById(
                                    ThreadLocalRandom
                                            .current()
                                            .nextLong(1, partsCount + 1))
                            .orElse(null)
            );
        }

        return parts;
    }
}
