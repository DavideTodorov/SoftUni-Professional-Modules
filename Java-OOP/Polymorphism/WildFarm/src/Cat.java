public class Cat extends Felime {

    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        super.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        return String.format("Cat[%s, %s, %s, %s, %d]",
                this.animalName, this.breed, this.animalWeight,
                this.livingRegion, this.foodEaten);
    }
}