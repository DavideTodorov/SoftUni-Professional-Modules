import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbers = Arrays.stream();

        String evenOrOdd = scanner.nextLine();

        Predicate<Integer> returnCorrectNumbers = createPredicate(evenOrOdd);

        System.out.println(Arrays.stream(numbers)
                .map(Integer::parseInt)
                .filter(returnCorrectNumbers)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static Predicate<Integer> createPredicate(String evenOrOdd) {
        if (evenOrOdd.equals("even")) {
            return e -> e % 2 == 0;
        }

        return e -> e % 2 != 0;
    }
}
