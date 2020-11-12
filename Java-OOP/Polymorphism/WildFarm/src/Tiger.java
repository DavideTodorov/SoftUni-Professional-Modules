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
        if (!food.getClass().getName().equals("Meat")) {
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }

        super.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        return String.format("Tiger[%s, %s, %s, %d]", this.animalName, this.animalWeight,
                this.livingRegion, this.foodEaten);
    }
}