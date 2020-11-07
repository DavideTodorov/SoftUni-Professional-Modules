public abstract class CarImpl implements Car {

    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduced;

    public CarImpl(String model, String color,
                   Integer horsePowerString, String countryProduced) {
        this.model = model;
        this.color = color;
        this.countryProduced = countryProduced;
        this.horsePower = horsePower;
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
        return String.format("%s is %s color and have %d horse power",
                model, color, horsePower) +
                System.lineSeparator() +
                String.format("This is %s produced in %s and have %d",
                        model, countryProduced, Car.TIRES) +
                System.lineSeparator();
    }
}
