import java.text.DecimalFormat;

public class Car extends Vehicle {

    public Car(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 0.9, tankCapacity);
    }


    public String drive(Double kilometers) {
        return super.drive(kilometers, "Car", super.getFuelConsumption());
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
