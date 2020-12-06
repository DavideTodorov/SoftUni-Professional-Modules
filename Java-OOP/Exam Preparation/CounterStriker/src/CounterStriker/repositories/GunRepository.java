package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> models;

    public GunRepository() {
        this.models = new HashMap<>();
    }


    @Override
    public Collection<Gun> getModels() {
        return null;
    }

    @Override
    public void add(Gun model) {

    }

    @Override
    public boolean remove(Gun model) {
        return false;
    }

    @Override
    public Gun findByName(String name) {
        return null;
    }
}
