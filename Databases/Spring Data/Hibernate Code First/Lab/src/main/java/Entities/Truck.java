package Entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Truck extends Vehicle {

    private double loadCapacity;

    public Set<Drivers> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Drivers> drivers) {
        this.drivers = drivers;
    }

    @ManyToMany
    private Set<Drivers> drivers;

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
