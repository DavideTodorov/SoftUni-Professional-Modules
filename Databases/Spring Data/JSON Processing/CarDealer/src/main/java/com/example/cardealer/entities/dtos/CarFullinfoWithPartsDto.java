package com.example.cardealer.entities.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarFullinfoWithPartsDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String travelledDistance;

    @Expose
    List<PartNameAndPriceDto> parts;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartNameAndPriceDto> getParts() {
        return parts;
    }

    public void setParts(List<PartNameAndPriceDto> parts) {
        this.parts = parts;
    }
}
