import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Lake lake = new Lake(numbers);

        StringBuilder sb = new StringBuilder();
        for (Lake it = lake; it.hasNext(); ) {
            int number = it.next();
            sb.append(number).append(", ");
        }

        System.out.println(sb.toString().substring(0, sb.toString().lastIndexOf(", ")));
    }
}