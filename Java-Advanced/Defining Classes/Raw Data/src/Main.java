import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputSize = Integer.parseInt(scanner.nextLine());

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < inputSize; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String name = tokens[0];

            int counter = 0;
            String[] engineArr = new String[2];
            for (int j = 1; j < 3; j++) {
                engineArr[counter++] = tokens[j];
            }

            counter = 0;
            String[] cargoArr = new String[2];
            for (int j = 3; j < 5; j++) {
                cargoArr[counter++] = tokens[j];
            }

            counter = 0;
            String[] tiresArr = new String[8];
            for (int j = 5; j < tokens.length; j++) {
                tiresArr[counter++] = tokens[j];
            }

            Car car = new Car(name, engineArr, cargoArr, tiresArr);
            cars.add(car);
        }

        String cargoType = scanner.nextLine();

        if (cargoType.equals("fragile")) {
            printFragileCars(cars);
        } else if (cargoType.equals("flamable")) {
            printFlamableCars(cars);
        }
    }

    private static void printFlamableCars(List<Car> cars) {
        cars.stream()
                .filter(x -> {
                    return x.getEngine().getPower() > 250;
                })
                .forEach(x -> System.out.println(x.toString()));
    }

    private static void printFragileCars(List<Car> cars) {
        cars.stream()
                .filter(x -> {
                    return x.getCargo().getCargoType().equals("fragile") &&
                            x.getTires().getFirstTirePressure() < 1 ||
                            x.getTires().getSecondTirePressure() < 1 ||
                            x.getTires().getThirdTirePressure() < 1 ||
                            x.getTires().getFourthTirePressure() < 1;
                })
                .forEach(x -> System.out.println(x.toString()));
    }
}
