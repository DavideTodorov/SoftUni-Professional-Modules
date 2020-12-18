import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AtomicInteger countOFUppercaseWords = new AtomicInteger();

        Predicate<String> checkStartsWithUppercase = s -> {
            boolean upperCase = Character.isUpperCase(s.charAt(0));
            if (upperCase) {
                countOFUppercaseWords.getAndIncrement();
            }
            return upperCase;
        };

        String collect = Arrays.stream(scanner.nextLine().split(" "))
                .filter(checkStartsWithUppercase)
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(countOFUppercaseWords.get());
        System.out.println(collect);

    }
}
