package easterRaces.repositories.interfaces;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseRepository<T> implements Repository<T> {
    private Collection<T> models;

    public BaseRepository() {
        models = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return null;
    }

    @Override
    public void add(T model) {

    }

    @Override
    public boolean remove(T model) {
        return false;
    }
}
