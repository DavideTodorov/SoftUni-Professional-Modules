package com.example.cardealer.entities.dtos;

import com.example.cardealer.entities.Supplier;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartInputDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private Integer quantity;

    @Expose
    private Supplier supplier;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
