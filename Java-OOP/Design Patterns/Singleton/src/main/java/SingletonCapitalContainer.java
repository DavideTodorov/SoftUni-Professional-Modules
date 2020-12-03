import java.util.HashMap;
import java.util.Map;

public class SingletonCapitalContainer {
    private static SingletonCapitalContainer instance;
    private Map<String, Integer> capitals;

    private SingletonCapitalContainer() {
        this.capitals = new HashMap<String, Integer>();
    }

    public static SingletonCapitalContainer getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new SingletonCapitalContainer();
        return instance;
    }

    public void increasePopulation(String capitalName, Integer count) {
        capitals.putIfAbsent(capitalName, 0);
        capitals.put(capitalName, capitals.get(capitalName) + count);
    }

    public void decreasePopulation(String capitalName, Integer count) {
        capitals.put(capitalName, capitals.get(capitalName) - count);
    }

    public Integer getCapitalPopulation(String capitalName) throws IllegalArgumentException {
        return capitals.get(capitalName);
    }
}
