import java.text.DecimalFormat;

public class Tiger extends Felime {

    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (!food.getClass().getSimpleName().equals("Meat")) {
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }

        super.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##########.###########");
        String formatted = decimalFormat.format(this.animalWeight);
        return String.format("Tiger[%s, %s, %s, %d]", this.animalName, formatted,
                this.livingRegion, this.foodEaten);
    }
}