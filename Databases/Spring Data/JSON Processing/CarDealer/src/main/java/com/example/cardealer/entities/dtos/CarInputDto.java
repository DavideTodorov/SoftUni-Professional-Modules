package com.example.cardealer.entities.dtos;

import com.example.cardealer.entities.Part;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Set;

public class CarInputDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String travelledDistance;

    @Expose
    @Size(min = 3, max = 5)
    private Set<Part> parts;

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

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
