import java.text.DecimalFormat;

public class Truck extends Vehicle {

    public Truck(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.6, tankCapacity);
    }


    public String drive(Double kilometers) {
        return super.drive(kilometers, "Truck", super.getFuelConsumption());
    }

    @Override
    public void refuel(Double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        double fuelQuantityAfterRefuel = super.getFuelQuantity() + (liters * 0.95);
        super.setFuelQuantity(fuelQuantityAfterRefuel);
    }
}
