import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String input = scanner.nextLine();
        while (!"end".equals(input)) {
            Consumer<int[]> executeCommand = createConsumer(input);

            executeCommand.accept(numbers);

            input = scanner.nextLine();
        }
    }

    private static Consumer<int[]> createConsumer(String input) {
        if (input.equals("add")) {
            return e -> {
                for (int i = 0; i < e.length; i++) {
                    e[i] = e[i] + 1;
                }
            };
        } else if (input.equals("multiply")) {
            return e -> {
                for (int i = 0; i < e.length; i++) {
                    e[i] = e[i] * 2;
                }
            };
        } else if (input.equals("subtract")) {
            return e -> {
                for (int i = 0; i < e.length; i++) {
                    e[i] = e[i] - 1;
                }
            };
        }

        return e -> {
            StringBuilder builder = new StringBuilder();

            for (int i : e) {
                builder.append(i).append(" ");
            }

            System.out.println(builder);
        };
    }
}
