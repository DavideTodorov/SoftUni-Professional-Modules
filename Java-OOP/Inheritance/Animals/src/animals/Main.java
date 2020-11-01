package animals;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animalTypeInput = scanner.nextLine();
        while (!"Beast!".equals(animalTypeInput)) {
            String[] animalCharacteristics = scanner.nextLine().split("\\s+");

            String animalName = animalCharacteristics[0];
            int animalAge = Integer.parseInt(animalCharacteristics[1]);
            String animalGender = animalCharacteristics[2];



            animalTypeInput = scanner.nextLine();
        }
    }
}
