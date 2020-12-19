import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Comparator<Integer> compareByEvenAndAscending = (e1, e2) -> {
            int result;

            if (e1 % 2 == 0 && e2 % 2 == 0) {
                result = Integer.compare(e1, e2);
            } else if (e1 % 2 != 0 && e2 % 2 == 0) {
                result = 1;
            } else if (e1 % 2 == 0 && e2 % 2 != 0) {
                result = -1;
            } else {
                result = Integer.compare(e1, e2);
            }

            return result;
        };


        System.out.println(numbers.stream()
                .sorted(compareByEvenAndAscending)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
