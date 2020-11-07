public class Seat implements Car {

    private String model;
    private String color;
    private String countryProduced;
    private Integer horsePower;

    public Seat(String model, String color, Integer horsePowerString, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getCountryProduced() {
        return countryProduced;
    }

    @Override
    public Integer getHorsePower() {
        return horsePower;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires",
                model, countryProduced, Car.TIRES);
    }
}
