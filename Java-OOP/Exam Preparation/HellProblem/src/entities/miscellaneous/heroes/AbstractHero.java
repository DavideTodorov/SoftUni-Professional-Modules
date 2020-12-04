package entities.miscellaneous.heroes;

import interfaces.Hero;
import interfaces.Item;
import interfaces.Recipe;

import java.util.Collection;

public abstract class AbstractHero implements Hero {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public long getStrength() {
        return 0;
    }

    @Override
    public long getAgility() {
        return 0;
    }

    @Override
    public long getIntelligence() {
        return 0;
    }

    @Override
    public long getHitPoints() {
        return 0;
    }

    @Override
    public long getDamage() {
        return 0;
    }

    @Override
    public Collection<Item> getItems() {
        return null;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void addRecipe(Recipe recipe) {

    }
}
