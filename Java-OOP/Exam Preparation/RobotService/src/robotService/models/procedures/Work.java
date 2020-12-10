package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public class Work extends BaseProcedure {
    public Work() {
        super();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }

        int newProcedureTime = robot.getProcedureTime() - procedureTime;
        robot.setProcedureTime(newProcedureTime);

        int energy = robot.getEnergy() - 6;
        int happiness = robot.getHappiness() + 12;
        
        robot.setEnergy(energy);
        robot.setHappiness(happiness);
    }

}
