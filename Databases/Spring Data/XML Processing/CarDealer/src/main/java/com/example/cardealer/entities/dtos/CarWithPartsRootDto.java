package com.example.cardealer.entities.dtos;

import com.example.cardealer.entities.dtos.inputDtos.CarWithPartsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsRootDto {

    @XmlElement(name = "car")
    private List<CarWithPartsDto> cars;

    public List<CarWithPartsDto> getCars() {
        return cars;
    }

    public void setCars(List<CarWithPartsDto> cars) {
        this.cars = cars;
    }
}
