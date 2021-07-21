package com.example.shopdatabase.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootDto {

    @XmlElement(name = "category")
    List<CategoryInputDto> categories;

    public List<CategoryInputDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryInputDto> categories) {
        this.categories = categories;
    }
}
