package com.example.cardealer.services;

import com.example.cardealer.entities.Supplier;
import com.example.cardealer.entities.dtos.SupplierIdNameAndPartsCountDto;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seedData() throws IOException;

    Supplier findRandom();

    List<SupplierIdNameAndPartsCountDto> getSuppliersThatArentImporters();
}
