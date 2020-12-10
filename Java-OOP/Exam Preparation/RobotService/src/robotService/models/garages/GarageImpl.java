package robotService.models.garages;

import robotService.models.garages.interfaces.Garage;
import robotService.models.robots.interfaces.Robot;

import java.util.LinkedHashMap;
import java.util.Map;

import static robotService.common.ExceptionMessages.*;

public class GarageImpl implements Garage {
    private static final int CAPACITY = 10;
    private Map<String, Robot> robots;

    public GarageImpl() {
        this.robots = new LinkedHashMap<>();
    }


    @Override
    public Map<String, Robot> getRobots() {
        return this.robots;
    }

    @Override
    public void manufacture(Robot robot) {
        if (robots.size() >= 10) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        String robotName = robot.getName();

        if (robots.containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(EXISTING_ROBOT, robotName));
        }

        robots.put(robotName, robot);
    }

    @Override
    public void sell(String robotName, String ownerName) {
        if (!robots.containsKey(robotName)){
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT,robotName));
        }

        Robot robot = robots.get(robotName);
        robot.setOwner(ownerName);
        robot.setBought(true);

        robots.remove(robotName);
    }
}
