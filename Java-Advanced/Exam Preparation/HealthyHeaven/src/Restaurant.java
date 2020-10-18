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
        boolean isSold = false;

        for (Salad salad : data) {
            if (salad.getName().equals(name)) {
                isSold = true;
                data.remove(salad);
                break;
            }
        }

        return isSold;
    }

    public String getHealthiestSalad() {
        Salad healthiestSalad = new Salad("");
        int minCalories = Integer.MAX_VALUE;

        for (Salad salad : data) {
            if (salad.getTotalCalories() < minCalories) {
                healthiestSalad = salad;
                minCalories = salad.getTotalCalories();
            }
        }

        return healthiestSalad.getName();
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
