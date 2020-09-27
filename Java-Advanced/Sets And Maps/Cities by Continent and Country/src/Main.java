import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputSize = Integer.parseInt(scanner.nextLine());

        Map<String, Map<String, List<String>>> allData = new LinkedHashMap<>();

        for (int i = 0; i < inputSize; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            String continent = input[0];
            String country = input[1];
            String city = input[2];

            allData.putIfAbsent(continent, new LinkedHashMap<>());
            allData.get(continent).putIfAbsent(country, new ArrayList<>());
            allData.get(continent).get(country).add(city);
        }

        allData.entrySet()
                .forEach(x -> {
                    System.out.println(x.getKey() + ":");

                    x.getValue()
                            .entrySet()
                            .forEach(innerX -> {
                                System.out.printf("  %s -> %s%n",
                                        innerX.getKey(),
                                        String.join(", ", innerX.getValue()));
                            });
                });
    }
}