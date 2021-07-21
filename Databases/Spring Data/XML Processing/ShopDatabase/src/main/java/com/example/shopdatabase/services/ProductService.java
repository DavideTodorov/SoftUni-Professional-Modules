package com.example.shopdatabase.services;

import com.example.shopdatabase.entities.dtos.ProductInputDto;
import com.example.shopdatabase.entities.dtos.ProductWithSellerDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedData(List<ProductInputDto> products);

    List<ProductWithSellerDto> findAllByPriceBetweenAndBuyerIsNull(BigDecimal lower, BigDecimal upper);
}
