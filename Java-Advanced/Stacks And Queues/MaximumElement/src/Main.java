import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int commandsCount = Integer.parseInt(scanner.nextLine());

        ArrayDeque<Integer> numbersStack = new ArrayDeque<>();

        for (int i = 0; i < commandsCount; i++) {
            String[] commandInput = scanner.nextLine().split("\\s+");
            String command = commandInput[0];

            switch (command) {
                case "1":
                    //Push
                    int element = Integer.parseInt(commandInput[1]);
                    numbersStack.push(element);
                    break;

                case "2":
                    //Delete
                    numbersStack.pop();
                    break;

                case "3":
                    //Print the maximum element
                    int maxElement = getMaxElement(numbersStack);
                    System.out.println(maxElement);
                    break;
            }
        }
    }

    private static int getMaxElement(ArrayDeque<Integer> numbersStack) {
        return numbersStack.stream()
                .max(Integer::compare).orElse(0);
    }
}
