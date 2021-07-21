package com.example.shopdatabase.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootDto {

    @XmlElement(name = "product")
    List<ProductInputDto> products;

    public List<ProductInputDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInputDto> products) {
        this.products = products;
    }
}
