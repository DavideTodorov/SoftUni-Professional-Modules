import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numbersCountToAdd = scanner.nextInt();
        int numbersCountToRemove = scanner.nextInt();
        int numberToCheck = scanner.nextInt();
        scanner.nextLine();

        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        for (int i = 0; i < numbersCountToAdd; i++) {
            numbers.offer(scanner.nextInt());
        }

        for (int i = 0; i < numbersCountToRemove; i++) {
            numbers.poll();
        }

        if (numbers.contains(numberToCheck)) {
            System.out.println(true);
        } else {
            int minNumber = numbers.stream()
                    .min(Integer::compare).orElse(0);
            System.out.println(minNumber);
        }
    }
}
