package com.example.exercise.entities.dtos;

import com.example.exercise.entities.Product;
import com.google.gson.annotations.Expose;

import java.util.List;

public class UserNamesAndProductsDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<ProductWithBuyerDto> productsSold;

    public UserNamesAndProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductWithBuyerDto> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(List<ProductWithBuyerDto> productsSold) {
        this.productsSold = productsSold;
    }
}
