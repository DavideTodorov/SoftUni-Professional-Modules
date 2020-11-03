package animals;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animalTypeInput = scanner.nextLine();
        while (!"Beast!".equals(animalTypeInput)) {
            String[] animalCharacteristics = scanner.nextLine().split("\\s+");

            if (animalCharacteristics.length < 3) {
                System.out.println("Invalid input!");
                animalTypeInput = scanner.nextLine();
                continue;
            }

            String animalName = animalCharacteristics[0];

            int animalAge;

            try {
                animalAge = Integer.parseInt(animalCharacteristics[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                animalTypeInput = scanner.nextLine();
                continue;
            }

            if (animalAge <= 0) {
                System.out.println("Invalid input!");
                animalTypeInput = scanner.nextLine();
                continue;
            }

            String animalGender = animalCharacteristics[2];
            if (!animalGender.equals("Male") && !animalGender.equals("Female")) {
                System.out.println("Invalid input!");
                animalTypeInput = scanner.nextLine();
                continue;
            }


            if (animalTypeInput.equals("Dog")) {
                Dog dog = new Dog(animalName, animalAge, animalGender);
                System.out.println(dog.asString());

            } else if (animalTypeInput.equals("Cat")) {
                Cat cat = new Cat(animalName, animalAge, animalGender);
                System.out.println(cat.asString());

            } else if (animalTypeInput.equals("Frog")) {
                Frog frog = new Frog(animalName, animalAge, animalGender);
                System.out.println(frog.asString());

            } else if (animalTypeInput.equals("Kitten")) {
                Kitten kitten = new Kitten(animalName, animalAge);
                System.out.println(kitten.asString());

            } else if (animalTypeInput.equals("Tomcat")) {
                Tomcat tomcat = new Tomcat(animalName, animalAge);
                System.out.println(tomcat.asString());

            } else {
                System.out.println("Invalid input!");
            }

            animalTypeInput = scanner.nextLine();
        }
    }
}