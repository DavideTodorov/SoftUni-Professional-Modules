package easterRaces.entities.racers;

import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    //SETTERS
    private void setLaps(int laps) {
        this.laps = laps;
    }

    private void setName(String name) {
        this.name = name;
    }


    //METHODS FROM RACE INTERFACE
    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getLaps() {
        return 0;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return null;
    }

    @Override
    public void addDriver(Driver driver) {

    }
}
