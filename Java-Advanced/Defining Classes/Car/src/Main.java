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

            int tokensSize = tokens.length;

            Car car = null;

            switch (tokensSize) {
                case 1:
                    car = new Car(tokens[0]);
                    break;

                case 2:
                    car = new Car(tokens[0], tokens[1]);
                    break;

                case 3:
                    car = new Car(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                    break;
            }
            cars.add(car);

        }
        cars.forEach(Car::carInfo);
    }
}
