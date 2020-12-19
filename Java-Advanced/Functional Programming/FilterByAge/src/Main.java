import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int peopleCount = Integer.parseInt(scanner.nextLine());

        String[] peopleArr = new String[peopleCount];

        for (int i = 0; i < peopleCount; i++) {
            peopleArr[i] = scanner.nextLine();
        }

        String filterType = scanner.nextLine();
        int ageFiltered = Integer.parseInt(scanner.nextLine());
        String formatToPrint = scanner.nextLine();

        Predicate<Integer> filterByAge = createFilterByAge(filterType, ageFiltered);
        Consumer<String[]> printConsumer = createConsumer(formatToPrint);

        Arrays.stream(peopleArr)
                .filter(e -> filterByAge.test(Integer.parseInt(e.split(",\\s+")[1])))
                .forEach(e -> printConsumer.accept(e.split(",\\s+")));

    }

    private static <T> Consumer<T[]> createConsumer(String formatToPrint) {
        if (formatToPrint.equals("name age")) {
            return e ->
                    System.out.printf("%s - %d%n", e[0], Integer.parseInt((String) e[1]));
        } else if (formatToPrint.equals("name")) {
            return e -> System.out.println(e[0]);
        }

        return e -> System.out.println(Integer.parseInt((String) e[1]));
    }

    private static Predicate<Integer> createFilterByAge(String filterType, int ageFiltered) {
        if (filterType.equals("older")) {
            return currAge -> currAge >= ageFiltered;
        }

        return currAge -> currAge <= ageFiltered;
    }
}
