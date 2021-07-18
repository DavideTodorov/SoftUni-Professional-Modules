package com.example.exercise.entities.dtos;

import com.google.gson.annotations.Expose;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInputDto {

    @Expose
    private String firstName;

    @Expose
    @NotNull
    @Size(min = 3)
    private String lastName;

    @Expose
    private int age;

    public UserInputDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
