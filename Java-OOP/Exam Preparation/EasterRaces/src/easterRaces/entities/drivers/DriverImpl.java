package easterRaces.entities.drivers;

import easterRaces.entities.cars.Car;

public abstract class DriverImpl implements Driver {
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.numberOfWins = 0;
        this.car = null;
        this.canParticipate = false;
    }

    //SETTER
    private void setName(String name) {
        this.name = name;
    }

    //METHODS FROM DRIVER INTERFACE
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Car getCar() {
        return null;
    }

    @Override
    public int getNumberOfWins() {
        return 0;
    }

    @Override
    public void addCar(Car car) {

    }

    @Override
    public void winRace() {

    }

    @Override
    public boolean getCanParticipate() {
        return false;
    }
}
