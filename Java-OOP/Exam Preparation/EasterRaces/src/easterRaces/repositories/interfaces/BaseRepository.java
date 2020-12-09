package easterRaces.repositories.interfaces;

import java.util.Collection;

public class BaseRepository implements Repository {
    @Override
    public Object getByName(String name) {
        return null;
    }

    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public void add(Object model) {

    }

    @Override
    public boolean remove(Object model) {
        return false;
    }
}
