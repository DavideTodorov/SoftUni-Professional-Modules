import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack stack = new Stack();

        String input = scanner.nextLine();
        while (!"END".equals(input)) {


            switch (input.split("\\s+")[0]) {
                case "Push":
                    pushElements(input.substring(5), stack);
                    break;

                case "Pop":
                    stack.pop();
                    break;
            }

            input = scanner.nextLine();
        }

        if (stack.size() > 0) {
            for (Integer integer : stack) {
                System.out.println(integer);
            }
        }
    }

    private static void pushElements(String input, Stack stack) {
        int[] ints = Arrays.stream(input.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        stack.push(ints);
    }
}