public class Audi extends CarImpl implements Rentable {

    private Integer minRentDay;
    private Double pricePerDay;

    public Audi(String model, String color,
                Integer horsePowerString, String countryProduced,
                Integer minRentDay, Double pricePerDay) {
        super(model, color, horsePowerString, countryProduced);
        this.minRentDay = minRentDay;
        this.pricePerDay = pricePerDay;
    }


    @Override
    public Integer getMinRentDay() {
        return minRentDay;
    }

    @Override
    public Double getPricePerDay() {
        return pricePerDay;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Minimum rental period of %d days. Price per day %s",
                minRentDay, pricePerDay);
    }
}
