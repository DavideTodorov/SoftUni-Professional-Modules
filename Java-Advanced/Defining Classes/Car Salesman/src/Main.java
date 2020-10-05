import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int enginesCount = Integer.parseInt(scanner.nextLine());

        Map<String, Engine> engines = new LinkedHashMap<>();

        for (int i = 0; i < enginesCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String engineModel = tokens[0];


            if (tokens.length == 2) {
                String power = tokens[1];
                Engine engine = new Engine(engineModel, power);
                engines.put(engineModel, engine);

            } else if (tokens.length == 3) {
                if (Character.isDigit(tokens[2].charAt(0))) {
                    String power = tokens[1];
                    String displacement = tokens[2];

                    Engine engine = new Engine(engineModel, power,
                            displacement, "n/a");
                    engines.put(engineModel, engine);

                } else {
                    String power = tokens[1];
                    String efficiency = tokens[2];

                    Engine engine = new Engine(engineModel, power,
                            "n/a", efficiency);
                    engines.put(engineModel, engine);
                }
            } else if (tokens.length == 4) {
                String power = tokens[1];
                String displacement = tokens[2];
                String efficiency = tokens[3];

                Engine engine = new Engine(engineModel, power,
                        displacement, efficiency);
                engines.put(engineModel, engine);
            }
        }

        int carCount = Integer.parseInt(scanner.nextLine());

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < carCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String carModel = tokens[0];
            String engineType = tokens[1];

            if (!engines.containsKey(engineType)) {
                continue;
            }

            Engine engine = engines.get(engineType);

            if (tokens.length == 2) {
                Car car = new Car(carModel, "n/a", "n/a", engine);
                cars.add(car);

            } else if (tokens.length == 3) {
                if (Character.isAlphabetic(tokens[2].charAt(0))) {
                    Car car = new Car(carModel, "n/a", tokens[2], engine);
                    cars.add(car);

                } else {
                    Car car = new Car(carModel, tokens[2], "n/a", engine);
                    cars.add(car);

                }
            } else if (tokens.length == 4) {
                Car car = new Car(carModel, tokens[2], tokens[3], engine);
                cars.add(car);
            }
        }

        cars.forEach(c -> System.out.println(c.toString()));
    }
}
