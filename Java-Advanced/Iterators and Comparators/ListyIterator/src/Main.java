import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        String[] toAdd = new String[tokens.length - 1];
        int indexToAdd = 0;
        for (int i = 1; i < tokens.length; i++) {
            toAdd[indexToAdd++] = tokens[i];
        }

        ListyIterator listyIterator = new ListyIterator(toAdd);

        String input = scanner.nextLine();
        while (!"END".equals(input)) {

            switch (input) {
                case "Move":
                    System.out.println(listyIterator.move());
                    break;

                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;

                case "Print":
                    System.out.println(listyIterator.print());
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
