package hell.entities.heroes;

public class Barbarian extends Heroes {
    private static final int BARBARIAN_STRENGTH = 90;
    private static final int BARBARIAN_AGILITY = 25;
    private static final int BARBARIAN_INTELLIGENCE = 10;
    private static final int BARBARIAN_HIT_POINTS = 350;
    private static final int BARBARIAN_DAMAGE = 150;

    public Barbarian(String name) {
        super(name, BARBARIAN_STRENGTH, BARBARIAN_AGILITY, BARBARIAN_INTELLIGENCE,
                BARBARIAN_HIT_POINTS, BARBARIAN_DAMAGE);
    }
}
