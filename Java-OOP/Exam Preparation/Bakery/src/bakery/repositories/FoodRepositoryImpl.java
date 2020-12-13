package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.*;

public class FoodRepositoryImpl<T extends BakedFood> implements FoodRepository<T> {
    private Map<String, T> models;

    public FoodRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public T getByName(String name) {
        return models.get(name);
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(T t) {
        String name = t.getName();
        models.put(name, t);
    }
}
