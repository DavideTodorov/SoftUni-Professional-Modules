package hell.instantiation.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.Collection;

public class Wizard extends HeroInventory implements Hero {
    private String name;
    private Integer strength;
    private Integer agility;
    private Integer intelligence;
    private Integer hitPoints;
    private Integer damage;
    private HeroInventory heroInventory;

    public Wizard(String name) {
        this.name = name;
        this.strength = 25;
        this.agility = 25;
        this.intelligence = 100;
        this.hitPoints = 100;
        this.damage = 250;
        this.heroInventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength;
    }

    @Override
    public long getAgility() {
        return this.agility;
    }

    @Override
    public long getIntelligence() {
        return this.intelligence;
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public long getDamage() {
        return this.damage;
    }

    @Override
    public Collection<Item> getItems() {
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
}
