import java.text.DecimalFormat;

public abstract class Vehicle {

    private Double tankCapacity;
    private Double fuelQuantity;
    private Double fuelConsumption;


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
    protected Double getTankCapacity() {
        return tankCapacity;
    }

    protected Double getFuelQuantity() {
        return fuelQuantity;
    }

    protected Double getFuelConsumption() {
        return fuelConsumption;
    }


    //ABSTRACT METHODS
    public String drive(Double kilometers, String vehicleType, Double fuelConsumption) {
        double fuelNeeded = fuelConsumption * kilometers;

        if (fuelNeeded < this.getFuelQuantity()) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(kilometers);

            double fuelLeft = this.getFuelQuantity() - fuelNeeded;
            this.setFuelQuantity(fuelLeft);
            return String.format("%s travelled %s km", vehicleType, formatted);
        }

        return vehicleType + " needs refueling";
    }

    public abstract void refuel(Double liters);
}
