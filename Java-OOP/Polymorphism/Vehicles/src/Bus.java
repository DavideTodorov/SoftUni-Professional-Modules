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
}
