package bakery.core;

import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.DrinkRepository;
import bakery.repositories.interfaces.FoodRepository;
import bakery.repositories.interfaces.TableRepository;

import java.util.Collection;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double completedBills;


    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository,
                          TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.completedBills = 0;
    }


    @Override
    public String addFood(String type, String name, double price) {
        BakedFood bakedFood = null;

        if (type.equals("Bread")) {
            bakedFood = new Bread(name, price);
        } else if (type.equals("Cake")) {
            bakedFood = new Cake(name, price);
        }

        Collection<BakedFood> all = foodRepository.getAll();
        for (BakedFood food : all) {
            if (food.getName().equals(name)) {
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
            }
        }

        foodRepository.add(bakedFood);

        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {

        Drink drink = null;
        if (type.equals("Tea")) {
            drink = new Tea(name, portion, brand);
        } else if (type.equals("Water")) {
            drink = new Water(name, portion, brand);
        }

        Collection<Drink> all = drinkRepository.getAll();
        for (Drink drink1 : all) {
            if (drink1.getName().equals(name)) {
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
            }
        }

        drinkRepository.add(drink);
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {

        Table table = null;
        if (type.equals("InsideTable")) {
            table = new InsideTable(tableNumber, capacity);
        } else if (type.equals("OutsideTable")) {
            table = new OutsideTable(tableNumber, capacity);
        }

        Collection<Table> all = tableRepository.getAll();
        for (Table table1 : all) {
            if (table1.getTableNumber() == tableNumber) {
                throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
            }
        }

        tableRepository.add(table);

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        boolean isFound = false;

        Collection<Table> all = tableRepository.getAll();

        Table table = null;
        for (Table currTable : all) {
            if (!currTable.isReserved() && currTable.getCapacity() >= numberOfPeople) {
                table = currTable;
                isFound = true;
                break;
            }
        }

        if (isFound) {
            table.reserve(numberOfPeople);
            return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
        } else {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = null;
        Collection<Table> all = tableRepository.getAll();

        for (Table currTable : all) {
            if (currTable.getTableNumber() == tableNumber && currTable.isReserved()) {
                table = currTable;
                break;
            }
        }

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        BakedFood bakedFood = null;

        Collection<BakedFood> allFood = foodRepository.getAll();
        for (BakedFood food : allFood) {
            if (food.getName().equals(foodName)) {
                bakedFood = food;
                break;
            }
        }

        if (bakedFood == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }

        table.orderFood(bakedFood);

        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = null;
        Collection<Table> all = tableRepository.getAll();

        for (Table currTable : all) {
            if (currTable.getTableNumber() == tableNumber && currTable.isReserved()) {
                table = currTable;
                break;
            }
        }

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Drink drink = null;

        Collection<Drink> allDrinks = drinkRepository.getAll();
        for (Drink currDrink : allDrinks) {
            if (currDrink.getName().equals(drinkName) && currDrink.getBrand().equals(drinkBrand)) {
                drink = currDrink;
                break;
            }
        }

        if (drink == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        table.orderDrink(drink);

        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        Collection<Table> allTables = tableRepository.getAll();

        Table table = null;
        for (Table currTable : allTables) {
            if (currTable.getTableNumber() == tableNumber) {
                table = currTable;
                break;
            }
        }

        if (table == null){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        StringBuilder builder = new StringBuilder();

        builder.append(String.format(BILL, tableNumber, table.getBill())).append(System.lineSeparator());

        completedBills += table.getBill();
        table.clear();

        return builder.toString().trim();
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder builder = new StringBuilder();

        Collection<Table> all = tableRepository.getAll();

        for (Table table : all) {
            if (!table.isReserved()) {
                builder.append(table.getFreeTableInfo()).append(System.lineSeparator());
            }
        }


        return builder.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, this.completedBills);
    }
}
