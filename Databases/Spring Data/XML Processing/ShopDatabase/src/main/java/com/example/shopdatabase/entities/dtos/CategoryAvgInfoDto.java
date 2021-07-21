package com.example.shopdatabase.entities.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryAvgInfoDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "products-count")
    private int count;

    @XmlElement(name = "average-price")
    private double averagePrice;

    @XmlElement(name = "total-revenue")
    private double totalRevenue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
