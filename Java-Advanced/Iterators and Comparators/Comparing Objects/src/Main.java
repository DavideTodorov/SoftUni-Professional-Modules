import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person> people = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");

            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            people.add(person);

            input = scanner.nextLine();
        }

        int personIndex = Integer.parseInt(scanner.nextLine()) - 1;
        int matches = 1;
        Person personToCompare = people.remove(personIndex);
        for (Person person : people) {
            if (personToCompare.compareTo(person) == 0) {
                matches++;
            }
        }

        if (matches > 1) {
            System.out.printf("%d %d %d%n", matches, (people.size() - matches + 1), people.size() + 1);
        } else {
            System.out.println("No matches");
        }
    }
}
