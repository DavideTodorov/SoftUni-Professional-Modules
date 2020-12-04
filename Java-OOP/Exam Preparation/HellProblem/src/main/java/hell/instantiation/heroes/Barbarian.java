package hell.instantiation.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.Collection;

public class Barbarian extends AbstractHero {
    public Barbarian(String name) {
        super(name, 90, 25, 10, 350, 150);
    }
}
