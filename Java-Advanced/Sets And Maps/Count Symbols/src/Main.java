import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("");

        Map<String, Integer> symbolOccurrences = new TreeMap<>();

        for (int i = 0; i < input.length; i++) {
            String currSymbol = input[i];

            symbolOccurrences.putIfAbsent(currSymbol, 0);
            symbolOccurrences.put(currSymbol, symbolOccurrences.get(currSymbol) + 1);
        }

        symbolOccurrences.entrySet()
                .forEach(x -> {
                    System.out.printf("%s: %d time/s%n", x.getKey(), x.getValue());
                });
    }
}