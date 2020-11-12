import java.text.DecimalFormat;

public class Car extends Vehicle {

    public Car(Double fuelQuantity, Double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 0.9);
    }

    @Override
    public String drive(Double kilometers) {
        double fuelNeeded = super.getFuelConsumption() * kilometers;

        if (fuelNeeded <= super.getFuelQuantity()){
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            String formatted = decimalFormat.format(kilometers);

            super.fuelQuantity -= fuelNeeded;
            return String.format("Car travelled %s km", formatted);
        }

        return "Car needs refueling";
    }

    @Override
    public void refuel(Double liters) {
        super.setFuelQuantity(liters);
    }
}
