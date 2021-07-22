package com.example.cardealer.entities.dtos.inputDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarInputRootDto {

    @XmlElement(name = "car")
    List<CarInputDto> cars;

    public List<CarInputDto> getCars() {
        return cars;
    }

    public void setCars(List<CarInputDto> cars) {
        this.cars = cars;
    }
}
