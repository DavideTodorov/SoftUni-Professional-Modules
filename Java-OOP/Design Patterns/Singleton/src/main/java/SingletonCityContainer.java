import java.util.HashMap;
import java.util.Map;

public class SingletonCityContainer {
    private static SingletonCityContainer instance;
    private Map<String, Integer> capitals;

    private SingletonCityContainer() {
        this.capitals = new HashMap<String, Integer>();
    }

    public static SingletonCityContainer getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new SingletonCityContainer();
        return instance;
    }

    public void increasePopulation(String cityName, Integer count) {
        capitals.putIfAbsent(cityName, 0);
        capitals.put(cityName, capitals.get(cityName) + count);
    }

    public void decreasePopulation(String cityName, Integer count) {
        capitals.put(cityName, capitals.get(cityName) - count);
    }

    public Integer getCapitalPopulation(String cityName) throws IllegalArgumentException {
        return capitals.get(cityName);
    }
}
