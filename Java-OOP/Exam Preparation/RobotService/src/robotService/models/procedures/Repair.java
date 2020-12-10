package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.ALREADY_REPAIRED;
import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public class Repair extends BaseProcedure {

    public Repair() {
        super();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {

        if (robot.isRepaired()) {
            throw new IllegalArgumentException(String.format(ALREADY_REPAIRED, robot.getName()));
        }

        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }

        int newProcedureTime = robot.getProcedureTime() - procedureTime;
        robot.setProcedureTime(newProcedureTime);
        robot.setRepaired(true);

        int newHappiness = robot.getHappiness() - 5;
        robot.setHappiness(newHappiness);

        robots.add(robot);
    }
}
