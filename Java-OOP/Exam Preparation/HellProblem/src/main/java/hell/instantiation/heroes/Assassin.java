package hell.instantiation.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.Collection;

public class Assassin extends AbstractHero {


    public Assassin(String name) {
        super(name, 25, 100, 15, 150, 300);
    }
}
