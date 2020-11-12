public abstract class Vehicle {

    protected Double tankCapacity;
    protected Double fuelQuantity;
    protected Double fuelConsumption;


    protected Vehicle(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        this.setTankCapacity(tankCapacity);
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }


    //SETTERS
    protected void setTankCapacity(Double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    protected void setFuelQuantity(Double fuelQuantity) {
        if (fuelQuantity <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (fuelQuantity > this.getTankCapacity()) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }


    //GETTERS
    public Double getTankCapacity() {
        return tankCapacity;
    }

    public Double getFuelQuantity() {
        return fuelQuantity;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }


    //ABSTRACT METHODS
    public abstract String drive(Double km);

    public abstract void refuel(Double liters);
}
