import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ToDoubleFunction<String> parseAndAddVAT = e -> 1.20 * Double.parseDouble(e);

        String collect = Arrays.stream(scanner.nextLine().split(", "))
                .mapToDouble(parseAndAddVAT)
                .boxed()
                .map(e -> String.format("%.2f", e))
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println("Prices with VAT:");
        System.out.println(collect);
    }
}
