import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Long>> countries = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!"report".equals(input)) {
            String[] tokens = input.split("\\|");

            String city = tokens[0];
            String country = tokens[1];
            long population = Long.parseLong(tokens[2]);

            countries.putIfAbsent(country, new LinkedHashMap<>());
            countries.get(country).putIfAbsent(city, 0L);
            countries.get(country).put(city, population);

            input = scanner.nextLine();
        }

        countries.entrySet()
                .stream()
                .sorted((c1, c2) -> {
                    long firstPopulation = c1.getValue().values()
                            .stream().mapToLong(i -> i).sum();

                    long secondPopulation = c2.getValue().values()
                            .stream().mapToLong(i -> i).sum();

                    return Long.compare(secondPopulation, firstPopulation);
                })
                .forEach(c -> {
                    long population = c.getValue().values()
                            .stream().mapToLong(i -> i).sum();
                    System.out.printf("%s (total population: %d)%n", c.getKey(), population);
                    c.getValue()
                            .entrySet()
                            .stream()
                            .sorted((innerC1, innerC2) -> {
                                long firstPopulation = innerC1.getValue();
                                long secondPopulation = innerC2.getValue();

                                return Long.compare(secondPopulation, firstPopulation);
                            })
                            .forEach(innerC -> {
                                System.out.printf("=>%s: %d%n", innerC.getKey(), innerC.getValue());
                            });
                });
    }
}