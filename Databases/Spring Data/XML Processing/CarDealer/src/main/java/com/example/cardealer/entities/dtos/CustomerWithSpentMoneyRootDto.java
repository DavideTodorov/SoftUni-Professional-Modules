package com.example.cardealer.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWithSpentMoneyRootDto {

    @XmlElement(name = "customer")
    private List<CustomerWithSpentMoneyDto> customers;

    public List<CustomerWithSpentMoneyDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithSpentMoneyDto> customers) {
        this.customers = customers;
    }
}
