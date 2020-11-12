public abstract class Vehicle {

    protected Double fuelQuantity;
    protected Double fuelConsumption;


    protected Vehicle(Double fuelQuantity, Double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuelQuantity(Double fuelQuantity) {
        this.fuelQuantity += fuelQuantity;
    }

    public Double getFuelQuantity() {
        return fuelQuantity;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public abstract String drive(Double km);

    public abstract void refuel(Double liters);
}
