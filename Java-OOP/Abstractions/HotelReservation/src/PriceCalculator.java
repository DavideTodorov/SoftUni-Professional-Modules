public class PriceCalculator {

    public static double calculate(double pricePerDay, int days, Season season, DiscountType discountType) {
        return pricePerDay * days * season.getMultiplier() * (1 - (discountType.getPercents() / 100.0));
    }

}
