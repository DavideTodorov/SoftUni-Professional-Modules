package com.example.cardealer.services;

import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.dtos.SupplierIdNameAndPartsCountDto;
import com.example.cardealer.entities.dtos.SupplierInputDto;
import com.example.cardealer.repositories.SupplierRepository;
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
public class SupplierServiceImpl implements SupplierService {
    private static final String SUPPLIER_FILE_PATH = "src/main/resources/suppliers.json";

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               ModelMapper modelMapper, ValidationUtil validationUtil,
                               Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {
        if (supplierRepository.count() > 0) {
            return;
        }

        Arrays.stream(
                gson.fromJson(Files.readString(Path.of(SUPPLIER_FILE_PATH)), SupplierInputDto[].class))
                .filter(validationUtil::isValid)
                .map(supplierInputDto -> modelMapper.map(supplierInputDto, Supplier.class))
                .forEach(supplierRepository::save);
    }

    @Override
    public Supplier findRandom() {
        return supplierRepository.findById(
                ThreadLocalRandom.current().nextLong(1, supplierRepository.count() + 1))
                .orElse(null);
    }

    @Override
    public List<SupplierIdNameAndPartsCountDto> getSuppliersThatArentImporters() {
        List<Supplier> allByImporterIsFalse = supplierRepository.findAllByImporterIsFalse();

        return allByImporterIsFalse
                .stream()
                .map(s -> {
                    SupplierIdNameAndPartsCountDto map =
                            modelMapper.map(s, SupplierIdNameAndPartsCountDto.class);
                    map.setPartsCount(s.getParts().size());
                    return map;
                }).collect(Collectors.toList());
    }
}
