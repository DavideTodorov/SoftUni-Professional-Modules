package robotService.models.procedures;

import robotService.models.procedures.BaseProcedure;
import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public class Charge extends BaseProcedure {
    public Charge() {
        super();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime){
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }

        int newProcedureTime = robot.getProcedureTime() - procedureTime;
        robot.setProcedureTime(newProcedureTime);

        int happiness = robot.getHappiness() + 12;
        int energy = robot.getEnergy() + 10;
        robot.setHappiness(happiness);
        robot.setEnergy(energy);
    }
}
