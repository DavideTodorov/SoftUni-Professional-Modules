import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> phoneNumbers = readListOfStrings(scanner);

        List<String> urls = readListOfStrings(scanner);

        Smartphone smartphone = new Smartphone(phoneNumbers, urls);

        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());

    }

    private static List<String> readListOfStrings(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
    }
}
