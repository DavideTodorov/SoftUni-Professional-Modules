import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carInfo = scanner.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(carInfo[1]),
                Double.parseDouble(carInfo[2]),
                Double.parseDouble(carInfo[3]));

        String[] truckInfo = scanner.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]),
                Double.parseDouble(truckInfo[2]),
                Double.parseDouble(truckInfo[3]));

        String[] busInfo = scanner.nextLine().split("\\s+");
        Bus bus = new Bus(Double.parseDouble(busInfo[1]),
                Double.parseDouble(busInfo[2]),
                Double.parseDouble(busInfo[3]));

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

                    case "Bus":
                        System.out.println(bus.drive(distance));
                        break;
                }
            } else if (command.equals("Refuel")) {
                double liters = Double.parseDouble(commandInput[2]);
                switch (vehicle) {
                    case "Car":
                        try {
                            car.refuel(liters);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "Truck":
                        try {
                            truck.refuel(liters);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "Bus":
                        try {
                            bus.refuel(liters);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }
            } else if (command.equals("DriveEmpty")) {
                double distance = Double.parseDouble(commandInput[2]);
                System.out.println(bus.driveEmpty(distance));
            }
        }

        System.out.println(String.format("Car: %.2f", car.getFuelQuantity()));
        System.out.println(String.format("Truck: %.2f", truck.getFuelQuantity()));
        System.out.println(String.format("Bus: %.2f", bus.getFuelQuantity()));
    }
}
