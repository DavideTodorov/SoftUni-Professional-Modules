package com.example.exercise.entities.dtos;


import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductInputDto {

    @Expose
    @Size(min = 3)
    private String name;

    @Expose
    private BigDecimal price;

    public ProductInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
