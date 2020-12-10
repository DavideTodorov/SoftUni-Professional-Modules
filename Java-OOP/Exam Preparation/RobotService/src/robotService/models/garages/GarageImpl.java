package robotService.models.garages;

import robotService.models.garages.interfaces.Garage;
import robotService.models.robots.interfaces.Robot;

import java.util.LinkedHashMap;
import java.util.Map;

public class GarageImpl implements Garage {
    private static final int CAPACITY = 10;
    private Map<String, Robot> robots;

    public GarageImpl() {
        this.robots = new LinkedHashMap<>();
    }

    

    @Override
    public Map<String, Robot> getRobots() {
        return null;
    }

    @Override
    public void manufacture(Robot robot) {

    }

    @Override
    public void sell(String robotName, String ownerName) {

    }
}
