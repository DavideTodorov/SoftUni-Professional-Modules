public class CarFacadeBuilder {
    private Car car;

    public CarFacadeBuilder() {
        this.car = new Car();
    }

    public CarFacadeBuilder withType(String type) {
        car.setType(type);
        return this;
    }

    public CarFacadeBuilder withColor(String color) {
        car.setColor(color);
        return this;
    }

    public CarFacadeBuilder withCity(String city) {
        car.setCity(city);
        return this;
    }

    public CarFacadeBuilder withStreet(String street) {
        car.setStreet(street);
        return this;
    }

    public CarFacadeBuilder withDoors(int doors) {
        car.setCountOfDoors(doors);
        return this;
    }

    public Car build(){
        return car;
    }
}
