import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int upperBound = Integer.parseInt(scanner.nextLine());
        int[] sequenceToTestWith = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Predicate<Integer> filterNumbers = e -> {
            for (int i = 0; i < sequenceToTestWith.length; i++) {
                if (e % sequenceToTestWith[i] != 0) {
                    return false;
                }
            }
            return true;
        };

        System.out.println(IntStream.range(1, upperBound + 1)
                .filter(filterNumbers::test)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

    }
}
