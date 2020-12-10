package robotService.models.robots;

import robotService.models.robots.interfaces.Robot;

public abstract class BaseRobot implements Robot {
    private String name;
    private int energy;
    private int happiness;
    private int procedureTime;
    private String owner;
    private boolean isBought;
    private boolean isRepaired;

    public BaseRobot(String name, int energy, int happiness, int procedureTime) {
        this.setName(name);
        this.setEnergy(energy);
        this.setHappiness(happiness);
        this.setProcedureTime(procedureTime);
        this.setOwner("Service");
        this.setBought(false);
        this.setRepaired(false);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getHappiness() {
        return 0;
    }

    @Override
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public int getProcedureTime() {
        return 0;
    }

    @Override
    public void setProcedureTime(int procedureTime) {
        this.procedureTime = procedureTime;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setBought(boolean bought) {
        this.isBought = bought;
    }

    @Override
    public boolean isRepaired() {
        return false;
    }

    @Override
    public void setRepaired(boolean repaired) {
        this.isRepaired = repaired;
    }

    @Override
    public String toString() {
        return "BaseRobot{}";
    }
}
