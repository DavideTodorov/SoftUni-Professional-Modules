package com.example.exercise.entities.dtos;

import java.math.BigDecimal;

public class GamePriceAndTitleDto {
    private String title;
    private BigDecimal price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", this.title, this.price);
    }
}
