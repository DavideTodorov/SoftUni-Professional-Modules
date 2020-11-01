package animals;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animalTypeInput = scanner.nextLine();
        while (!"Beast!".equals(animalTypeInput)) {
            String[] animalCharacteristics = scanner.nextLine().split("\\s+");


            String animalName = animalCharacteristics[0];
            int animalAge = Integer.parseInt(animalCharacteristics[1]);

            if (animalCharacteristics.length <= 2 && !animalTypeInput.equals("Tomcat") || !animalTypeInput.equals("Kitten")) {
                System.out.println("Invalid Input!");
                animalTypeInput = scanner.nextLine();
                continue;
            }
            
            String animalGender = animalCharacteristics[2];

            if (animalName == null || animalAge <= 0) {
                System.out.println("Invalid Input!");
                animalTypeInput = scanner.nextLine();
                continue;
            }

            if (animalTypeInput.equals("Dog")) {
                Dog dog = new Dog(animalName, animalAge, animalGender);
                printCharacteristics(animalTypeInput, animalName, animalAge, animalGender);
                dog.produceSound();

            } else if (animalTypeInput.equals("Cat")) {
                Cat cat = new Cat(animalName, animalAge, animalGender);
                printCharacteristics(animalTypeInput, animalName, animalAge, animalGender);
                cat.produceSound();

            } else if (animalTypeInput.equals("Frog")) {
                Frog frog = new Frog(animalName, animalAge, animalGender);
                printCharacteristics(animalTypeInput, animalName, animalAge, animalGender);
                frog.produceSound();
            } else if (animalTypeInput.equals("Kitten")) {
                Kitten kitten = new Kitten(animalName, animalAge, "Female");
                printCharacteristics(animalTypeInput, animalName, animalAge, "Female");
                kitten.produceSound();
            } else if (animalTypeInput.equals("Tomcat")) {
                Tomcat tomcat = new Tomcat(animalName, animalAge, "Male");
                printCharacteristics(animalTypeInput, animalName, animalAge, "Male");
                tomcat.produceSound();
            }

            animalTypeInput = scanner.nextLine();
        }
    }

    private static void printCharacteristics(String animalTypeInput, String animalName,
                                             int animalAge, String animalGender) {
        System.out.println(animalTypeInput);
        System.out.println(String.format("%s %d %s", animalName, animalAge, animalGender));
    }
}