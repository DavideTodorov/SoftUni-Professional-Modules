import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AtomicInteger countOfElements = new AtomicInteger();
        AtomicInteger sumOfElements = new AtomicInteger();

        Consumer<String> parseToIntAndCalculateSize = e -> {
            countOfElements.getAndIncrement();
            sumOfElements.getAndAdd(Integer.parseInt(e));
        };

        Arrays.stream(scanner.nextLine().split(", "))
                .forEach(parseToIntAndCalculateSize);

        System.out.println("Count = " + countOfElements.get());
        System.out.println("Sum = " + sumOfElements.get());
    }
}
