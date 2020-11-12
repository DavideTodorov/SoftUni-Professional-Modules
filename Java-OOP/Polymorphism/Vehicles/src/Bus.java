import java.text.DecimalFormat;

public class Bus extends Vehicle {

    public Bus(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }


    public String driveEmpty(Double km) {
        double fuelNeeded = super.getFuelConsumption() * km;

        if (fuelNeeded <= super.getFuelQuantity()) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(km);

            super.fuelQuantity -= fuelNeeded;
            return String.format("Bus travelled %s km", formatted);
        }

        return "Bus needs refueling";
    }


    @Override
    public String drive(Double km) {
        double fuelNeeded = (super.getFuelConsumption() + 1.4) * km;

        if (fuelNeeded <= super.getFuelQuantity()) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(km);

            super.fuelQuantity -= fuelNeeded;
            return String.format("Bus travelled %s km", formatted);
        }

        return "Bus needs refueling";
    }

    @Override
    public void refuel(Double liters) {
        double fuelQuantityAfterRefuel = super.getFuelQuantity() + liters;
        super.setFuelQuantity(fuelQuantityAfterRefuel);
    }
}
