package com.example.exercise.services;

import com.example.exercise.entities.dtos.ProductNameAndPriceDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedData() throws IOException;

    List<ProductNameAndPriceDto> findAllWithPriceInRange(BigDecimal lower, BigDecimal upper);
}
