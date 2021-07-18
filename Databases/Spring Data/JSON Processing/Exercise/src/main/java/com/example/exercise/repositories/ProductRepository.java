package com.example.exercise.repositories;

import com.example.exercise.entities.Product;
import com.example.exercise.entities.dtos.ProductNameAndPriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p " +
            "where p.buyer is null and p.price between :lower and :upper " +
            "order by p.price asc ")
    List<Product> findAllByPriceBetween(BigDecimal lower, BigDecimal upper);

}
