package easterRaces.repositories;

import easterRaces.entities.cars.BaseCar;
import easterRaces.entities.cars.Car;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.sql.DriverAction;
import java.util.ArrayList;
import java.util.Collection;


public abstract class BaseRepository<T> implements Repository<T> {
    private Collection<T> models;

    public BaseRepository() {
        models = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        for (T model : models) {
            if (model.getClass().isInstance(BaseCar.class)) {
                Car car = (Car) model;

                if (car.getModel().equals(name)) {
                    return (T) car;
                }

            } else if (model.getClass().isInstance(DriverImpl.class)) {
                Driver driver = (Driver) model;

                if (driver.getName().equals(name)) {
                    return (T) driver;
                }

            } else if (model.getClass().isInstance(RaceImpl.class)) {
                Race race = (Race) model;

                if (race.getName().equals(name)) {
                    return (T) race;
                }
            }
        }

        return null;
    }

    @Override
    public Collection<T> getAll() {
        return models;
    }

    @Override
    public void add(T model) {
        models.add(model);
    }

    @Override
    public boolean remove(T model) {
        for (T currModel : models) {
            if (currModel.equals(model)){
                models.remove(model);
                return true;
            }
        }
        return false;
    }
}
