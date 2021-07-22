package com.example.cardealer.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarMakeModelAndDistanceRootDto {

    @XmlElement(name = "car")
    private List<CarMakeModelAndDistanceDto> cars;

    public List<CarMakeModelAndDistanceDto> getCars() {
        return cars;
    }

    public void setCars(List<CarMakeModelAndDistanceDto> cars) {
        this.cars = cars;
    }
}
