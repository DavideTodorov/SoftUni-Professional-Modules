package barracksWars.core.commands;

import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;

public abstract class Commands implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    protected Commands(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    protected String add() {
        String unitType = data[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }

    protected String report() {
        String output = this.repository.getStatistics();
        return output;
    }

    protected String fight() {
        return "fight";
    }
}
