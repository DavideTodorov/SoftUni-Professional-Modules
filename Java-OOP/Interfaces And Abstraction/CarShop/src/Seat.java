public class Seat extends CarImpl implements Sellable {

    private Double price;

    public Seat(String model, String color, Integer horsePowerString,
                String countryProduced, Double price) {
        super(model, color, horsePowerString, countryProduced);
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%s sells for %s",
                this.getModel(), price);
    }
}