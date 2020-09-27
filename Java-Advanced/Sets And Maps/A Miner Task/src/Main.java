import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> resources = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!"stop".equals(input)) {
            int quantity = Integer.parseInt(scanner.nextLine());

            resources.putIfAbsent(input, 0);
            resources.put(input, resources.get(input) + quantity);

            input = scanner.nextLine();
        }

        resources.entrySet()
                .forEach(x -> {
                    System.out.printf("%s -> %d%n", x.getKey(), x.getValue());
                });
    }
}
