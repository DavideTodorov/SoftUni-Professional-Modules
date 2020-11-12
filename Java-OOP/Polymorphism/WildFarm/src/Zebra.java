public class Zebra extends Mammal {

    public Zebra(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        if (!food.getClass().getName().equals("Vegetable")) {
            throw new IllegalArgumentException("Zebras are not eating that type of food!");
        }

        super.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        return String.format("Zebra[%s, %s, %s, %d]", this.animalName, this.animalWeight,
                this.livingRegion, this.foodEaten);
    }
}