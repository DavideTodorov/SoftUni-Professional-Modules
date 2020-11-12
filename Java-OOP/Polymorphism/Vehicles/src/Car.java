import java.text.DecimalFormat;

public class Car extends Vehicle {

    public Car(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 0.9, tankCapacity);
    }

    @Override
    public String drive(Double kilometers) {
        double fuelNeeded = super.getFuelConsumption() * kilometers;

        if (fuelNeeded < super.getFuelQuantity()) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(kilometers);

            double fuelLeft = super.getFuelQuantity() - fuelNeeded;
            super.setFuelQuantity(fuelLeft);
            return String.format("Car travelled %s km", formatted);
        }

        return "Car needs refueling";
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
