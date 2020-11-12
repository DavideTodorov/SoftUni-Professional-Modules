import java.text.DecimalFormat;

public class Bus extends Vehicle {

    public Bus(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }


    public String driveEmpty(Double kilometers) {
        return super.drive(kilometers, "Bus", super.getFuelConsumption());
    }


    public String drive(Double kilometers) {
        return super.drive(kilometers, "Bus", (super.getFuelConsumption() + 1.4));
    }

    @Override
    public void refuel(Double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        double fuelQuantityAfterRefuel = super.getFuelQuantity() + liters;
        super.setFuelQuantity(fuelQuantityAfterRefuel);
    }
}
