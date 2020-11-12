import java.text.DecimalFormat;

public class Truck extends Vehicle {

    public Truck(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.6, tankCapacity);
    }

    @Override
    public String drive(Double kilometers) {
        double fuelNeeded = super.getFuelConsumption() * kilometers;

        if (fuelNeeded < super.getFuelQuantity()) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(kilometers);

            double fuelLeft = super.getFuelQuantity()- fuelNeeded;
            super.setFuelQuantity(fuelLeft);
            return String.format("Truck travelled %s km", formatted);
        }

        return "Truck needs refueling";
    }

    @Override
    public void refuel(Double liters) {
        if (liters <= 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        double fuelQuantityAfterRefuel = super.getFuelQuantity() + (liters * 0.95);
        super.setFuelQuantity(fuelQuantityAfterRefuel);
    }
}
