package com.example.cardealer.entities.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerTotalSalesDto {

    @Expose
    @SerializedName("full_name")
    private String name;

    @Expose
    private int boughtCars;

    @Expose
    private Double spentMoney;

    public CustomerTotalSalesDto() {
    }

    public CustomerTotalSalesDto(String s, int parseInt, double parseDouble) {
        this.name = s;
        this.boughtCars = parseInt;
        this.spentMoney = parseDouble;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public Double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(Double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
