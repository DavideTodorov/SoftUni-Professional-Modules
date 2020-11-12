public class Car extends Vehicle {

    public Car(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 0.9, tankCapacity);
    }


    public String drive(Double kilometers) {
        return super.drive(kilometers, "Car", super.getFuelConsumption());
    }
}
