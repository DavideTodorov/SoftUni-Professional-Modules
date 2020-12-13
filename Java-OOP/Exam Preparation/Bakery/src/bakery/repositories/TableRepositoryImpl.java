package bakery.repositories;

import java.util.*;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

public class TableRepositoryImpl<T extends Table> implements TableRepository<T> {
    private Map<Integer, T> models;

    public TableRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }


    @Override
    public T getByNumber(int number) {
        return models.get(number);
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(T t) {
        models.put(t.getTableNumber(), t);
    }
}
