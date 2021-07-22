package com.example.cardealer.entities.dtos.inputDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierInputRootDto {

    @XmlElement(name = "supplier")
    List<SupplierInputDto> suppliers;

    public List<SupplierInputDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierInputDto> suppliers) {
        this.suppliers = suppliers;
    }
}
