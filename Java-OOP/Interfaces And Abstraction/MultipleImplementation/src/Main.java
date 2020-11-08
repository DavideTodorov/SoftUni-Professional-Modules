import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Birthable> birthables = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"End".equalsIgnoreCase(input)) {
            String[] tokens = input.split("\\s+");

            String inputType = tokens[0];
            String name = tokens[1];

            switch (inputType) {
                case "Citizen":
                    int age = Integer.parseInt(tokens[2]);
                    String id = tokens[3];
                    String birthDay = tokens[4];

                    birthables.add(new Citizen(name, age, id, birthDay));
                    break;

                case "Pet":
                    birthDay = tokens[2];
                    birthables.add(new Pet(name, birthDay));
                    break;
            }

            input = scanner.nextLine();
        }

        String yearToSearchFor = scanner.nextLine();
        
        for (Birthable birthable : birthables) {
            if (birthable.getBirthDate().endsWith(yearToSearchFor)){
                System.out.println(birthable.getBirthDate());
            }
        }
    }
}
