import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> firstBox = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondBox = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));


        int loot = 0;

        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            int firstNum = firstBox.pollFirst();
            int secondNum = secondBox.pollLast();

            int sum = firstNum + secondNum;

            if (sum % 2 == 0) {
                loot += sum;
            } else {
                firstBox.offerFirst(firstNum);
                firstBox.offer(secondNum);
            }
        }

        if (firstBox.isEmpty()) {
            System.out.println("First lootbox is empty");
        } else {
            System.out.println("Second lootbox is empty");
        }

        if (loot >= 100) {
            System.out.println(String.format("Your loot was epic! Value: %d", loot));
        } else {
            System.out.println(String.format("Your loot was poor... Value: %d", loot));
        }
    }
}