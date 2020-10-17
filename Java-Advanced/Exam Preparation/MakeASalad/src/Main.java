import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> vegetableQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> caloriesStack = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> caloriesMade = new ArrayDeque<>();

        int tomatoCals = 80;
        int carrotCals = 136;
        int lettuceCals = 109;
        int potatoCals = 215;

        while (!vegetableQueue.isEmpty() && !caloriesStack.isEmpty()) {
            int calories = caloriesStack.pollLast();
            int startingCalories = calories;

            while (calories > 0 && !vegetableQueue.isEmpty()) {
                String vegetable = vegetableQueue.poll();
                switch (vegetable) {
                    case "tomato":
                        calories -= tomatoCals;
                        break;

                    case "carrot":
                        calories -= carrotCals;
                        break;

                    case "lettuce":
                        calories -= lettuceCals;
                        break;

                    case "potato":
                        calories -= potatoCals;
                        break;
                }
            }

            if (calories <= 0) {
                caloriesMade.offer(startingCalories);
            }
        }


        if (!caloriesMade.isEmpty()) {
            for (int i = caloriesMade.size() - 1; i >= 0; i--) {
                System.out.print(caloriesMade.poll() + " ");
            }
            System.out.println();
        }

        if (!vegetableQueue.isEmpty()) {
            for (String s : vegetableQueue) {
                System.out.print(s + " ");
            }
            System.out.println();
        } else {
            for (int i = caloriesStack.size() - 1; i >= 0; i--) {
                System.out.print(caloriesStack.pollLast() + " ");
            }
            System.out.println();
        }

    }
}
