package com.example.cardealer.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @OneToOne()
    private Car car;

    @ManyToOne
    private Customer customer;

    public Sale() {
    }

    public Sale(Car randomCar, Customer randomCustomer, double i) {
        this.car = randomCar;
        this.customer = randomCustomer;
        this.discountPercentage = i;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
