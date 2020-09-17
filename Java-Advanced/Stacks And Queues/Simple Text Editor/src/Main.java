import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = "";
        ArrayDeque<String> undone = new ArrayDeque<>();

        int operationCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < operationCount; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String command = input[0];

            switch (command) {
                case "1":
                    String toPut = input[1];
                    undone.push(text);
                    text = text + toPut;
                    break;

                case "2":
                    int count = Integer.parseInt(input[1]);
                    int lastIndex = text.length() - count;
                    undone.push(text);
                    String erased = text.substring(0, lastIndex);
                    text = erased;
                    break;

                case "3":
                    int index = Integer.parseInt(input[1]) - 1;
                    char toPrint = text.charAt(index);
                    System.out.println(toPrint);
                    break;

                case "4":
                    text = undone.pop();
                    break;
            }
        }
    }
}
