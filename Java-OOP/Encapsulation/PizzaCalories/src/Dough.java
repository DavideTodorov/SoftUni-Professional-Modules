import java.util.HashMap;
import java.util.Map;

public class Dough {

    private String flourType;
    private String bakingTechnique;
    private double weight;
    private double cals = 1;

    private Map<String, Double> flourModifiers = new HashMap<>();


    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
        addAllflourTypeModifiers();
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }

        this.weight = weight;
    }

    private void setBakingTechnique(String bakingTechnique) {
        validateFlourType(flourType);
        this.bakingTechnique = bakingTechnique;
    }

    private void setFlourType(String flourType) {
        validateFlourType(flourType);

        this.flourType = flourType;
    }

    private void validateFlourType(String flourType) {
        if (!flourModifiers.containsKey(flourType)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

        this.cals = this.cals * flourModifiers.get(flourType);
    }

    private void addAllflourTypeModifiers() {
        this.flourModifiers.put("White", 1.5);
        this.flourModifiers.put("Wholegrain", 1.0);
        this.flourModifiers.put("Crispy", 0.9);
        this.flourModifiers.put("Chewy", 1.1);
        this.flourModifiers.put("Homemade", 1.0);
    }

    public double calculateCalories() {
        return 2 * this.cals * this.weight;
    }
}
