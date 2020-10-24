import java.util.ArrayList;
import java.util.List;

public class Salad {

    private String name;
    private List<Vegetable> products;

    public Salad(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getTotalCalories() {
        int totalCalories = 0;

        for (Vegetable product : this.products) {
            totalCalories += product.getCalories();
        }

        return totalCalories;
    }

    public int getProductCount() {
        return this.products.size();
    }

    public void add(Vegetable product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        String format = String.format("* Salad %s is %d calories and have %d products:", this.name,
                this.getTotalCalories(),
                this.getProductCount());

        result.append(format).append(System.lineSeparator());

        for (Vegetable product : this.products) {
            result.append(product.toString()).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}