import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IntPredicate isEven = e -> e % 2 == 0;

        String collect = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .filter(isEven)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));

        System.out.println(collect);

        System.out.println(Arrays.stream(collect.split(", "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
}
