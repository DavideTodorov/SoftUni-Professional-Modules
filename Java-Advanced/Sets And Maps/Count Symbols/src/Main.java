import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Character, Integer> symbolOccurrences = new TreeMap<>();

        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char currSymbol = input.charAt(i);

            symbolOccurrences.putIfAbsent(currSymbol, 0);
            int occurrences = symbolOccurrences.get(currSymbol) + 1;
            symbolOccurrences.put(currSymbol, occurrences);
        }

        symbolOccurrences.forEach((key, value) -> System.out.printf("%c: %d time/s%n", key, value));
    }
}