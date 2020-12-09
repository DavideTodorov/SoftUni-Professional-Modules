package easterRaces.entities.cars;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.setCubicCentimeters(cubicCentimeters);
    }

    //SETTERS
    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    private void setModel(String model) {
        this.model = model;
    }


    //METHODS FROM CAR INTERFACE
    @Override
    public String getModel() {
        return null;
    }

    @Override
    public int getHorsePower() {
        return 0;
    }

    @Override
    public double getCubicCentimeters() {
        return 0;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return 0;
    }
}
