import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private List<Salad> data;

    public Restaurant(String name) {
        this.name = name;
        this.data = new ArrayList<>();
    }

    public void add(Salad salad) {
        this.data.add(salad);
    }

    public boolean buy(String name) {
        return this.data.removeIf(salad -> salad.getName().equals(name));
    }

    public Salad getHealthiestSalad() {
        Salad healthiestSalad = null;
        int minCalories = Integer.MAX_VALUE;

        for (Salad salad : data) {
            if (salad.getTotalCalories() < minCalories) {
                healthiestSalad = salad;
                minCalories = salad.getTotalCalories();
            }
        }

        return healthiestSalad;
    }

    public String generateMenu() {
        StringBuilder result = new StringBuilder();

        String format = String.format("%s have %d salads:", this.name, this.data.size());
        result.append(format).append(System.lineSeparator());

        for (int i = 0; i < this.data.size(); i++) {
            if (i == this.data.size() - 1) {
                result.append(data.get(i));
            } else {
                result.append(data.get(i)).append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
