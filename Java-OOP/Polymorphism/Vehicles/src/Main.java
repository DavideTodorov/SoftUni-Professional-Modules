import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(carInfo[1]),
                Double.parseDouble(carInfo[2]));

        String[] truckInfo = scanner.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]),
                Double.parseDouble(truckInfo[2]));

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] commandInput = scanner.nextLine().split("\\s+");

            String command = commandInput[0];
            String vehicle = commandInput[1];

            if (command.equals("Drive")) {
                double distance = Double.parseDouble(commandInput[2]);
                switch (vehicle) {
                    case "Car":
                        System.out.println(car.drive(distance));
                        break;

                    case "Truck":
                        System.out.println(truck.drive(distance));
                        break;
                }
            } else if (command.equals("Refuel")) {
                double liters = Double.parseDouble(commandInput[2]);
                switch (vehicle) {
                    case "Car":
                        car.refuel(liters);
                        break;

                    case "Truck":
                        truck.refuel(liters);
                        break;
                }
            }
        }

        System.out.println(String.format("Car: %.2f", car.getFuelQuantity()));
        System.out.println(String.format("Truck: %.2f", truck.getFuelQuantity()));
    }
}
