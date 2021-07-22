package com.example.cardealer.services;

import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.dtos.SupplierNameAndPartsCountDto;
import com.example.cardealer.entities.dtos.inputDtos.SupplierInputDto;

import java.util.List;

public interface SupplierService {

    void seedData(List<SupplierInputDto> suppliers);

    Supplier getRandomSupplier();

    List<SupplierNameAndPartsCountDto> findAllByImporterIsFalse();
}
