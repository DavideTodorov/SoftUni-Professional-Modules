import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputCount = Integer.parseInt(scanner.nextLine());


        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < inputCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            Car car = new Car();

            car.setBrand(tokens[0]);
            car.setModel(tokens[1]);
            car.setHorsePower(Integer.parseInt(tokens[2]));

            cars.add(car);
        }

        cars.stream().forEach(car -> System.out.println(String.format("The car is: %s %s - %d HP.",
                car.getBrand(), car.getModel(), car.getHorsePower())));
    }
}
