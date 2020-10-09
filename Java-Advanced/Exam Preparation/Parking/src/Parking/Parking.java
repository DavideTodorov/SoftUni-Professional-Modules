package Parking;

import java.util.LinkedHashMap;
import java.util.Map;

public class Parking {

    private String type;
    private int capacity;
    private Map<String, Car> data;

    public Parking(String parkingType, int parkingCapacity) {
        this.type = parkingType;
        this.capacity = parkingCapacity;
        this.data = new LinkedHashMap<>();
    }

    public void add(Car car) {
        if (this.data.size() < capacity) {
            this.data.put(car.getManufacturer(), car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        boolean isRemoved = false;
        if (data.containsKey(manufacturer)) {
            if (data.get(manufacturer).getModel().equals(model)) {
                data.remove(manufacturer);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    public Car getLatestCar() {
        Car latestCar = new Car();

        if (data.isEmpty()) {
            return null;
        }

        int latestYear = Integer.MIN_VALUE;
        for (Map.Entry<String, Car> car : data.entrySet()) {
            int currCarYear = car.getValue().getYear();
            if (currCarYear > latestYear) {
                latestCar = car.getValue();
                latestYear = currCarYear;
            }
        }
        return latestCar;
    }

    public Car getCar(String manufacturer, String model) {

        if (data.containsKey(manufacturer)) {
            if (data.get(manufacturer).getModel().equals(model)) {
                return data.get(manufacturer);
            }
        }

        return null;
    }

    public int getCount() {
        return data.size();
    }


    public String getStatistics() {
        StringBuilder carsToPrint = new StringBuilder();

        for (Car value : data.values()) {
            carsToPrint.append(value.toString()).append("\n");
        }
        return String.format("The cars are parked in %s:\n%s", this.type, carsToPrint);
    }
}
