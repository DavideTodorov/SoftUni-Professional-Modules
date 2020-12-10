package robotService.models.procedures;

import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseProcedure implements Procedure {
    private Collection<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.getClass().getSimpleName()).append(System.lineSeparator());

        for (Robot robot : robots) {
            builder.append(robot.toString())
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
