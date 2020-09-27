import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputLines = Integer.parseInt(scanner.nextLine());

        Set<String> elements = new TreeSet<>();


        for (int i = 0; i < inputLines; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            for (int elementsInput = 0; elementsInput < tokens.length; elementsInput++) {
                String currElement = tokens[elementsInput];
                elements.add(currElement);
            }
        }

        System.out.println(String.join(" ", elements));
    }
}
