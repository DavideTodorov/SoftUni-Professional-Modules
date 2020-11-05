import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Topping {

    private Map<String, Double> toppingsModifiers = new HashMap<>();


    private String toppingType;
    private double weight;
    private double cals;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
        addAllModifiers();
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].",
                    this.toppingType));
        }

        this.weight = weight;
    }

    private void addAllModifiers() {
        this.toppingsModifiers.put("Meat", 1.2);
        this.toppingsModifiers.put("Veggies", 0.8);
        this.toppingsModifiers.put("Cheese", 1.1);
        this.toppingsModifiers.put("Sauce", 0.9);
    }

    private void setToppingType(String toppingType) {
        if (!toppingsModifiers.containsKey(toppingType)) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.",
                    toppingType));
        }

        this.toppingType = toppingType;
        this.cals = toppingsModifiers.get(toppingType);
    }
}
