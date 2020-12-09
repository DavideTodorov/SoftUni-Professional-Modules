package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.*;

public class DriverRepository implements Repository<Driver> {
    private Map<String, Driver> driverRepository;

    public DriverRepository() {
        this.driverRepository = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return driverRepository.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return driverRepository.values();
    }

    @Override
    public void add(Driver model) {
        driverRepository.put(model.getName(), model);
    }

    @Override
    public boolean remove(Driver model) {
        return driverRepository.remove(model.getName(), model);
    }
}
