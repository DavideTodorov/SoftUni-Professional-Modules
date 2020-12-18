import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IntStream numbers = IntStream.range(scanner.nextInt(), scanner.nextInt() + 1);
        scanner.nextLine();

        int[] ints = numbers.toArray();

        Predicate<Integer> predicate = createPredicate(scanner.nextLine());

        System.out.println(Arrays.stream(ints)
                .boxed()
                .filter(predicate)
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
