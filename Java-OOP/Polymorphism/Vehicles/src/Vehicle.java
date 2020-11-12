public abstract class Vehicle {

    private Double fuelQuantity;
    private Double fuelConsumption;
    private Double litersPerKm;

    protected Vehicle(Double fuelQuantity, Double fuelConsumption, Double litersPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.litersPerKm = litersPerKm;
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

    public Double getLitersPerKm() {
        return litersPerKm;
    }

    public abstract String drive(Double km);
    public abstract void refuel(Double liters);
}
