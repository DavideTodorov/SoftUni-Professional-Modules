package com.example.cardealer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private boolean isYoungDriver;

    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
