package com.example.cardealer.entities.dtos.inputDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInputRootDto {

    @XmlElement(name = "customer")
    List<CustomerInputDto> customers;

    public List<CustomerInputDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerInputDto> customers) {
        this.customers = customers;
    }
}
