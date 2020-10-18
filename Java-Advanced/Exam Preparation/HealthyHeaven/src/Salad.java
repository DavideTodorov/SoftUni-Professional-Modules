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

        for (Vegetable product : products) {
            totalCalories += product.getCalories();
        }

        return totalCalories;
    }

    public int getProductCount() {
        return this.products.size();
    }

    public void add(Vegetable vegetable) {
        this.products.add(vegetable);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        String format = String.format("* Salad %s is %d calories and have %d products:", this.name,
                this.getTotalCalories(),
                this.getProductCount());

        result.append(format).append(System.lineSeparator());

        for (int i = 0; i < this.products.size(); i++) {
            if (i == this.products.size() - 1) {
                result.append(products.get(i));
            } else {
                result.append(products.get(i)).append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}