package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {
    private Map<String, Car> carRepository;

    public CarRepository() {
        this.carRepository = new HashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return carRepository.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return carRepository.values();
    }

    @Override
    public void add(Car model) {
        carRepository.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        return carRepository.remove(model.getModel(), model);
    }
}
