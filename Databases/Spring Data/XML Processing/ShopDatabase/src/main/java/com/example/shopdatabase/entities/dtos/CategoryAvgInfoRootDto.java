package com.example.shopdatabase.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryAvgInfoRootDto {

    @XmlElement(name = "category")
    private List<CategoryAvgInfoDto> categories;

    public List<CategoryAvgInfoDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryAvgInfoDto> categories) {
        this.categories = categories;
    }
}
