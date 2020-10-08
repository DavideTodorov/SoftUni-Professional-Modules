import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parking {

    private String parkingType;
    private int parkingCapacity;
    private Map<String, Car> cars;

    public Parking(String parkingType, int parkingCapacity) {
        this.parkingType = parkingType;
        this.parkingCapacity = parkingCapacity;
        this.cars = new LinkedHashMap<>();
    }

    public void add(Car car) {
        if (this.cars.size() < parkingCapacity) {
            this.cars.put(car.getManufacturer(), car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        boolean isRemoved = false;
        if (cars.containsKey(manufacturer)) {
            if (cars.get(manufacturer).getModel().equals(model)) {
                cars.remove(manufacturer);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public Car getLatestCar() {
        Car latestCar = new Car();

        if (cars.isEmpty()) {
            return null;
        }

        int latestYear = Integer.MIN_VALUE;
        for (Map.Entry<String, Car> car : cars.entrySet()) {
            int currCarYear = car.getValue().getYear();
            if (currCarYear > latestYear) {
                latestCar = car.getValue();
                latestYear = currCarYear;
            }
        }
        return latestCar;
    }

    public Car getCar(String manufacturer, String model) {

        if (cars.containsKey(manufacturer)) {
            if (cars.get(manufacturer).getModel().equals(model)) {
                return cars.get(manufacturer);
            }
        }

        return null;
    }

    public int getCount() {
        return cars.size();
    }


    public String getStatistics() {
        StringBuilder carsToPrint = new StringBuilder();

        for (Car value : cars.values()) {
            carsToPrint.append(value.toString()).append("\n");
        }
        return String.format("The cars are parked in %s:\n%s", this.parkingType, carsToPrint);
    }
}
