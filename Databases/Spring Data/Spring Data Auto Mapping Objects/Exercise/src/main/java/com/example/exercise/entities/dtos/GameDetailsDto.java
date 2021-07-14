package com.example.exercise.entities.dtos;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameDetailsDto {
    private String title;
    private BigDecimal price;
    private String info;
    private LocalDate date;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("Title: %s%n" +
                        "Price: %.2f%n" +
                        "Description: %s%n" +
                        "Release date: %s", this.title, this.price,
                this.info, this.date.format(format));
    }
}
