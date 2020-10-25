import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tasksStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(tasksStack::push);

        ArrayDeque<Integer> threadsQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int valueOfTask = Integer.parseInt(scanner.nextLine());

        while (!tasksStack.isEmpty() && !threadsQueue.isEmpty()) {
            int taskValue = tasksStack.peek();
            int threadValue = threadsQueue.peek();

            if (taskValue == valueOfTask) {
                System.out.printf("Thread with value %d killed task %d\n", threadValue, valueOfTask);
                break;
            }

            if (threadValue >= taskValue) {
                tasksStack.pop();
                threadsQueue.poll();
            } else if (threadValue < taskValue) {
                threadsQueue.poll();
            }
        }

        threadsQueue.forEach(t -> System.out.print(t + " "));
    }
}