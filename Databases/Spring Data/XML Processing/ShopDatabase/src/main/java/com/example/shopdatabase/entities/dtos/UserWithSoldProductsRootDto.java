package com.example.shopdatabase.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsRootDto {

     @XmlElement(name = "user")
     List<UserWithSoldProductsDto> userWithSoldProducts;

     public List<UserWithSoldProductsDto> getUserWithSoldProducts() {
          return userWithSoldProducts;
     }

     public void setUserWithSoldProducts(List<UserWithSoldProductsDto> userWithSoldProducts) {
          this.userWithSoldProducts = userWithSoldProducts;
     }
}
