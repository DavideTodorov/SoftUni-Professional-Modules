import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String animalInput = scanner.nextLine();
        while (!"End".equals(animalInput)) {
            String[] animalTokens = animalInput.split("\\s+");
            String[] foodInputTokens = scanner.nextLine().split("\\s+");

            Animal animal = createAnimal(animalTokens);

            Food food = createFood(foodInputTokens);

            animal.makeSound();
            try {
                animal.eat(food);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            animals.add(animal);
            
            animalInput = scanner.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private static Food createFood(String[] foodInputTokens) {
        int quantity = Integer.parseInt(foodInputTokens[1]);

        return foodInputTokens[0].equals("Vegetable")
                ? new Vegetable(quantity)
                : new Meat(quantity);
    }

    private static Animal createAnimal(String[] animalTokens) {
        String animalType = animalTokens[0];

        switch (animalType) {
            case "Tiger":
                return new Tiger(animalTokens[1], animalType,
                        Double.parseDouble(animalTokens[2]),
                        animalTokens[3]);

            case "Zebra":
                return new Zebra(animalTokens[1], animalType,
                        Double.parseDouble(animalTokens[2]),
                        animalTokens[3]);

            case "Mouse":
                return new Mouse(animalTokens[1], animalType,
                        Double.parseDouble(animalTokens[2]),
                        animalTokens[3]);

            case "Cat":
                return new Cat(animalTokens[1], animalType,
                        Double.parseDouble(animalTokens[2]),
                        animalTokens[3], animalTokens[4]);
        }
        return null;
    }
}
