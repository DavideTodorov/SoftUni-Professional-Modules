package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.*;

import static CounterStriker.common.ExceptionMessages.INVALID_GUN_REPOSITORY;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> models;

    public GunRepository() {
        this.models = new HashMap<>();
    }


    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }

        models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        return models.remove(model.getName(), model);
    }

    @Override
    public Gun findByName(String name) {
        Gun gun = null;

        for (Gun currGun : models.values()) {
            if (currGun.getName().equals(name)) {
                gun = currGun;
                break;
            }
        }

        return gun;
    }

    @Override
    public Collection<Gun> getModels() {
        return new ArrayList<>(models.values());
    }
}
