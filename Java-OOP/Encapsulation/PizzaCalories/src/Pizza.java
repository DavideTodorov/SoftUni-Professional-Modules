import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private String name;
    private Dough dough;
    private List<Topping> toppings;
    private int numberOfToppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setNumberOfToppings(numberOfToppings);
        this.toppings = new ArrayList<>(numberOfToppings);
    }

    public String getName() {
        return name;
    }

    private void setNumberOfToppings(int numberOfToppings) {
        if (numberOfToppings < 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }

        this.numberOfToppings = numberOfToppings;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }

        this.name = name;
    }

    public void addTopping(Topping topping) {
        if (this.toppings.size() < this.numberOfToppings) {
            this.toppings.add(topping);
        }
    }

    public double getOverallCalories() {
        double totalCalories = 0;
        for (Topping topping : toppings) {
            totalCalories += topping.calculateCalories();
        }

        totalCalories += dough.calculateCalories();

        return totalCalories;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f",
                this.name, this.getOverallCalories());
    }
}