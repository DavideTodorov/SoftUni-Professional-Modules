import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> calculator = new ArrayDeque();

        String[] input = scanner.nextLine().split("\\s+");

        for (int i = input.length - 1; i >= 0; i--) {
            calculator.push(input[i]);
        }

        while (calculator.size() > 1) {
            int firstNum = Integer.parseInt(calculator.pop());
            char symbol = calculator.pop().charAt(0);
            int secondNum = Integer.parseInt(calculator.pop());

            int result = 0;

            if (symbol == '+') {
                result = firstNum + secondNum;
            } else if (symbol == '-') {
                result = firstNum - secondNum;
            }

            calculator.push(String.valueOf(result));
        }

        System.out.println(String.join("", calculator));
    }
}
