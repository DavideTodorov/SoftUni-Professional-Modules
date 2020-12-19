import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        int numToDivideWith = Integer.parseInt(scanner.nextLine());
        Predicate<Integer> filterDivisibleNums = e -> e % numToDivideWith != 0;


        numbers = numbers.stream()
                .filter(filterDivisibleNums)
                .collect(Collectors.toList());
        
        Collections.reverse(numbers);


        String collect = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));


        System.out.println(collect);
    }
}
