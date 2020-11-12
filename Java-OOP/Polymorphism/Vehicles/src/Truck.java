import java.text.DecimalFormat;

public class Truck extends Vehicle {

    public Truck(Double fuelQuantity, Double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 1.6);
    }

    @Override
    public String drive(Double kilometers) {
        double fuelNeeded = super.getFuelConsumption() * kilometers;

        if (fuelNeeded <= super.getFuelQuantity()) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(kilometers);

            super.fuelQuantity -= fuelNeeded;
            return String.format("Truck travelled %s km", formatted);
        }

        return "Truck needs refueling";
    }

    @Override
    public void refuel(Double liters) {
        super.setFuelQuantity(liters * 0.95);
    }
}
