package com.example.exercise.entities.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CategoryInputDto {

    @Expose
    @Size(min = 3)
    private String name;

    public CategoryInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
