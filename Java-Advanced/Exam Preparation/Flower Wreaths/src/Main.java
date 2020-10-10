import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> liliesQueue = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> rosesStack = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));


        int minLength = Math.min(liliesQueue.size(), rosesStack.size());
        int wreathsCounter = 0;
        int storedFlowers = 0;

        while (minLength > 0) {
            int lastLilies = liliesQueue.pollLast();
            int lastRoses = rosesStack.pop();
            int flowersCount = lastLilies + lastRoses;

            if (flowersCount == 15) {
                wreathsCounter++;

            } else if (flowersCount < 15) {
                storedFlowers += flowersCount;

            } else if (flowersCount > 15) {
                flowersCount = decreaseLiliesAre15(flowersCount);
                if (flowersCount == 15) {
                    wreathsCounter++;
                } else if (flowersCount < 15) {
                    storedFlowers += flowersCount;
                }
            }

            minLength--;
        }

        wreathsCounter += (storedFlowers / 15);
        if (wreathsCounter >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!%n", wreathsCounter);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!%n", 5 - wreathsCounter);
        }
    }

    private static int decreaseLiliesAre15(int flowersCount) {
        flowersCount -= 2;
        if (flowersCount == 15) {
            return flowersCount;
        } else if (flowersCount > 15) {
            flowersCount = decreaseLiliesAre15(flowersCount);
        }
        return flowersCount;
    }
}