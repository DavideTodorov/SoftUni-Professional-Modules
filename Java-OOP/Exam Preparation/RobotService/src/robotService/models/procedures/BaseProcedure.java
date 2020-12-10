package robotService.models.procedures;

import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.Collection;
import java.util.HashMap;

public abstract class BaseProcedure implements Procedure {
    private HashMap<String, Robot> robots;

    public BaseProcedure() {
        this.robots = new HashMap<>();
    }

    @Override
    public String history() {
        return null;
    }
}
