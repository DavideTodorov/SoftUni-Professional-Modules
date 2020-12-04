package hell.entities.heroes;

public class Assassin extends Heroes {
    private static final int ASSASSIN_STRENGTH = 25;
    private static final int ASSASSIN_AGILITY = 100;
    private static final int ASSASSIN_INTELLIGENCE = 15;
    private static final int ASSASSIN_HIT_POINTS = 150;
    private static final int ASSASSIN_DAMAGE = 300;

    public Assassin(String name) {
        super(name, ASSASSIN_STRENGTH, ASSASSIN_AGILITY, ASSASSIN_INTELLIGENCE,
                ASSASSIN_HIT_POINTS, ASSASSIN_DAMAGE);
    }
}
