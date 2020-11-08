import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> identifiables = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");

            if (tokens.length == 3) {
                //Citizen
                identifiables.add(new Citizen(tokens[0],
                        Integer.parseInt(tokens[1]), tokens[2]));

            } else if (tokens.length == 2) {
                //Robot
                identifiables.add(new Robot(tokens[0], tokens[1]));
            }

            input = scanner.nextLine();
        }

        String idPostfix = scanner.nextLine();

        for (Identifiable identifiable : identifiables) {
            if (identifiable.getId().endsWith(idPostfix)) {
                System.out.println(identifiable.getId());
            }
        }
    }
}
