import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> vegetableQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> caloriesStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(caloriesStack::push);

        ArrayDeque<Integer> caloriesMade = new ArrayDeque<>();

        int tomatoCals = 80;
        int carrotCals = 136;
        int lettuceCals = 109;
        int potatoCals = 215;

        while (!vegetableQueue.isEmpty() && !caloriesStack.isEmpty()) {
            int calories = caloriesStack.poll();
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
            for (int i = vegetableQueue.size() - 1; i >= 0; i--) {
                System.out.println(vegetableQueue.pollFirst() + " ");
            }
            System.out.println();
        }

        if (!caloriesStack.isEmpty()) {
            for (int i = caloriesStack.size() - 1; i >= 0; i--) {
                System.out.print(caloriesStack.pollLast() + " ");
            }
            System.out.println();
        }

    }
}
