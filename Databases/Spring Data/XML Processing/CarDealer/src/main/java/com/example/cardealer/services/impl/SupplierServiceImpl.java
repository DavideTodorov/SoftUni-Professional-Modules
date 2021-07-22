package com.example.cardealer.services.impl;

import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.dtos.SupplierNameAndPartsCountDto;
import com.example.cardealer.entities.dtos.SupplierNameAndPartsCountRootDto;
import com.example.cardealer.entities.dtos.inputDtos.SupplierInputDto;
import com.example.cardealer.repositories.SupplierRepository;
import com.example.cardealer.services.SupplierService;
import com.example.cardealer.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {


    private final SupplierRepository supplierRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidationUtil validationUtil,
                               ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedData(List<SupplierInputDto> suppliers) {
        if (supplierRepository.count() > 0) {
            return;
        }

        List<Supplier> collect = suppliers
                .stream()
                .filter(validationUtil::isValid)
                .map(supplierInputDto -> modelMapper.map(supplierInputDto, Supplier.class))
                .collect(Collectors.toList());

        supplierRepository.saveAll(collect);
    }

    @Override
    public Supplier getRandomSupplier() {
        return supplierRepository.findById(
                ThreadLocalRandom
                        .current()
                        .nextLong(1, supplierRepository.count() + 1))
                .orElse(null);
    }

    @Override
    public List<SupplierNameAndPartsCountDto> findAllByImporterIsFalse() {
        return supplierRepository
                .findAllByImporterIsFalse()
                .stream()
                .map(supplier -> {
                    SupplierNameAndPartsCountDto map =
                            modelMapper.map(supplier, SupplierNameAndPartsCountDto.class);
                    map.setPartsCount(supplier.getParts().size());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
