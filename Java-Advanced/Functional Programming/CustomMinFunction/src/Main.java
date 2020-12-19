import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Function<int[], Integer> findSmallest = e -> {
            for (int i = 0; i < e.length; i++) {
                for (int j = i + 1; j < e.length; j++) {
                    if (e[i] > e[j]) {
                        int temp = e[i];
                        e[i] = e[j];
                        e[j] = temp;
                    }
                }
            }
            return e[0];
        };

        int[] ints = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(findSmallest.apply(ints));
    }
}
