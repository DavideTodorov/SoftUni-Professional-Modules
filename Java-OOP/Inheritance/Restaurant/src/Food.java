import java.math.BigDecimal;

public class Food extends Product {

    private double grams;

    public Food(String name, double price, double grams) {
        super(name, new BigDecimal(price));
        this.grams = grams;
    }

    public double getGrams() {
        return this.grams;
    }
}