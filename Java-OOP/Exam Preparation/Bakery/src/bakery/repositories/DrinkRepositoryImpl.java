package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.*;

public class DrinkRepositoryImpl<T extends Drink> implements DrinkRepository<T> {
    private Map<String, T> models;

    public DrinkRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public T getByNameAndBrand(String drinkName, String drinkBrand) {
        T drink = null;

        for (T currDrink : models.values()) {
            if (currDrink.getName().equals(drinkName) && currDrink.getBrand().equals(drinkBrand)) {
                drink = currDrink;
                break;
            }
        }
        return  drink;
    }

    @Override
    public Collection<T> getAll() {
        Collection<T> values = models.values();
        return Collections.unmodifiableCollection(values);
    }

    @Override
    public void add(T t) {
        String name = t.getName();
        models.put(name, t);
    }
}
