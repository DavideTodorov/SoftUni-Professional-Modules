package Entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Plane extends Vehicle{

    private int passengerCapacity;

    @ManyToOne
    private Companies companies;

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }
}
