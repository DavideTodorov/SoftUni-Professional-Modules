package com.example.cardealer.entities.dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerFullInfoDto {

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private String birthDate;

    @Expose
    private boolean isYoungDriver;

    @Expose
    List<SaleInfoDto> sales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public List<SaleInfoDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleInfoDto> sales) {
        this.sales = sales;
    }
}
