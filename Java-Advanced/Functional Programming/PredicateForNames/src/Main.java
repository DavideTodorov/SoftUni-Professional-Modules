import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int minLength = Integer.parseInt(scanner.nextLine());

        Predicate<String> filterWordsShorterThanMinimum = e -> e.length() <= minLength;

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(filterWordsShorterThanMinimum)
                .forEach(System.out::println);
    }
}
