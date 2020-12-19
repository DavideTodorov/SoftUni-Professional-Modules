import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> people = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!"Party!".equals(input)) {
            String[] tokens = input.split("\\s+");

            String commandType = tokens[0];
            String commandDefinition = tokens[1];

            Predicate<String> predicate;

            switch (commandType) {
                case "Remove":
                    predicate =
                            getPredicate(tokens, commandDefinition);
                    people.removeIf(predicate);
                    break;

                case "Double":
                    predicate =
                            getPredicate(tokens, commandDefinition);

                    break;
            }

            input = scanner.nextLine();
        }

        if (people.isEmpty()) {
            System.out.println("Nobody is going to the party!");
            return;
        }

        System.out.println(people.stream()
                .collect(Collectors.joining(", ")) + " are going to the party!");
    }

    private static Predicate<String> getPredicate(String[] tokens, String commandDefinition) {
        Predicate<String> predicate = null;

        if (commandDefinition.equals("StartsWith")) {
            String partToSearch = tokens[2];
            predicate = e -> e.startsWith(partToSearch);
        } else if (commandDefinition.equals("EndsWith")) {
            String partToSearch = tokens[2];
            predicate = e -> e.endsWith(partToSearch);
        } else if (commandDefinition.equals("Length")) {
            int length = Integer.parseInt(tokens[2]);
            predicate = e -> e.length() == length;
        }

        return predicate;
    }
}
