package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public abstract class Heroes implements Hero {
    private String name;
    private Integer strength;
    private Integer agility;
    private Integer intelligence;
    private Integer hitPoints;
    private Integer damage;
    private HeroInventory heroInventory;

    public Heroes(String name, Integer strength, Integer agility,
                  Integer intelligence, Integer hitPoints, Integer damage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.heroInventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + heroInventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + heroInventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + heroInventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + heroInventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + heroInventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() {
        try {
            Field field = this.heroInventory.getClass().getDeclaredField("commonItems");
            field.setAccessible(true);
            return ((Map<String, Item>) field.get(this.heroInventory)).values();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addItem(Item item) {
        heroInventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        heroInventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Hero: ").append(this.getName()).append(", Class: ").append(this.getClass().getSimpleName()).append(System.lineSeparator());
        output.append("HitPoints: ").append(this.getHitPoints()).append(", Damage: ").append(this.getDamage()).append(System.lineSeparator());
        output.append("Strength: ").append(this.getStrength()).append(System.lineSeparator());
        output.append("Agility: ").append(this.getAgility()).append(System.lineSeparator());
        output.append("Intelligence: ").append(this.getIntelligence()).append(System.lineSeparator());

        if (this.getItems().isEmpty()) {
            output.append("Items: None");
        } else {
            output.append("Items:").append(System.lineSeparator());
            for (Item item : this.getItems()) {
                output.append(item.toString());
            }
        }

        return output.toString().trim();
    }
}
