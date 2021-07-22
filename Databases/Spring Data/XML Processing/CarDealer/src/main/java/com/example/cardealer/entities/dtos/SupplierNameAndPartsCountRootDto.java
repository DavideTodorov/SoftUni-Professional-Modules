package com.example.cardealer.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierNameAndPartsCountRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierNameAndPartsCountDto> suppliers;

    public List<SupplierNameAndPartsCountDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierNameAndPartsCountDto> suppliers) {
        this.suppliers = suppliers;
    }
}
