package easterRaces.repositories;

import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RaceRepository implements Repository<Race> {
    private Map<String, Race> raceRepository;

    public RaceRepository() {
        this.raceRepository = new HashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return raceRepository.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return raceRepository.values();
    }

    @Override
    public void add(Race model) {
        raceRepository.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        return raceRepository.remove(model.getName(), model);
    }
}
