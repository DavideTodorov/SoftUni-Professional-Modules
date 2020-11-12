import java.text.DecimalFormat;

public class Mouse extends Mammal {

    public Mouse(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        if (!food.getClass().getName().equals("Vegetable")) {
            throw new IllegalArgumentException("Mice are not eating that type of food!");
        }

        super.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##########.###########");
        String formatted = decimalFormat.format(this.animalWeight);
        return String.format("Mouse[%s, %s, %s, %d]", this.animalName, formatted,
                this.livingRegion, this.foodEaten);
    }
}