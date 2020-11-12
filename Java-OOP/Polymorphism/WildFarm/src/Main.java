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

            if (animalTokens.length == 5) {
                //CAT
                Cat cat = new Cat(animalTokens[1], animalTokens[0],
                        Double.parseDouble(animalTokens[2]), animalTokens[3],
                        animalTokens[4]);

                animals.add(cat);
                cat.makeSound();
                animalEats(foodInputTokens, cat);
//                System.out.println(cat.toString());
            } else {
                //MOUSE, TIGER OR ZEBRA
                switch (animalTokens[0]) {
                    case "Tiger":
                        Tiger tiger = new Tiger(animalTokens[1], animalTokens[0],
                                Double.parseDouble(animalTokens[2]), animalTokens[3]);

                        animals.add(tiger);
                        tiger.makeSound();
                        try {
                            animalEats(foodInputTokens, tiger);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

//                        System.out.println(tiger.toString());
                        break;

                    case "Zebra":
                        Zebra zebra = new Zebra(animalTokens[1], animalTokens[0],
                                Double.parseDouble(animalTokens[2]), animalTokens[3]);

                        animals.add(zebra);
                        zebra.makeSound();
                        try {
                            animalEats(foodInputTokens, zebra);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

//                        System.out.println(zebra.toString());
                        break;

                    case "Mouse":
                        Mouse mouse = new Mouse(animalTokens[1], animalTokens[0],
                                Double.parseDouble(animalTokens[2]), animalTokens[3]);

                        animals.add(mouse);
                        mouse.makeSound();
                        try {
                            animalEats(foodInputTokens, mouse);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

//                        System.out.println(mouse.toString());
                        break;
                }
            }

            animalInput = scanner.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private static void animalEats(String[] foodInputTokens, Animal animal) {
        int foodQuantity = Integer.parseInt(foodInputTokens[1]);

        switch (foodInputTokens[0]) {
            case "Vegetable":
                animal.eat(new Vegetable(foodQuantity));
                break;

            case "Meat":
                animal.eat(new Meat(foodQuantity));
                break;
        }
    }
}
