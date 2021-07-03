package Entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Car extends Vehicle{

    private int seats;

    @OneToOne
    private PlateNumber plateNumber;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
