package com.example.exercise.entities.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoriesInfoDto {

    @Expose
    private String category;

    @Expose
    private int productsCount;

    @Expose
    private Double averagePrice;

    @Expose
    private Double totalRevenue;

    public CategoriesInfoDto() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
