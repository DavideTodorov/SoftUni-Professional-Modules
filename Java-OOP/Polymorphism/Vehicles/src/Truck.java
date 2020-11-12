public class Truck extends Vehicle {

    public Truck(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.6, tankCapacity);
    }


    public String drive(Double kilometers) {
        return super.drive(kilometers, "Truck", super.getFuelConsumption());
    }

    @Override
    public void refuel(Double liters) {
        super.refuel(liters * 0.95);
    }
}
