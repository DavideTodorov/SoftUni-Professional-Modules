package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;

public class GunRepository implements Repository<Gun> {
    private Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
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
