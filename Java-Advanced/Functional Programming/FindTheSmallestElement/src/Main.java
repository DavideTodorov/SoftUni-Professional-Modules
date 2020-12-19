import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Function<int[], Integer> findIndexOfSmallestElement = e -> {
            int smallestElement = Integer.MAX_VALUE;
            for (int i = 0; i < e.length; i++) {
                if (e[i] < smallestElement) {
                    smallestElement = e[i];
                }
            }

            int index = 0;
            for (int i = e.length - 1; i >= 0; i--) {
                if (e[i] == smallestElement) {
                    index = i;
                    break;
                }
            }
            return index;
        };

        System.out.println(findIndexOfSmallestElement.apply(arr));
    }
}
