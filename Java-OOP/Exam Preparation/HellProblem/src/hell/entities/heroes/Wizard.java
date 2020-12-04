package hell.entities.heroes;

public class Wizard extends Heroes {
    private static final int WIZARD_STRENGTH = 25;
    private static final int WIZARD_AGILITY = 25;
    private static final int WIZARD_INTELLIGENCE = 100;
    private static final int WIZARD_HIT_POINTS = 100;
    private static final int WIZARD_DAMAGE = 250;

    public Wizard(String name) {
        super(name, WIZARD_STRENGTH, WIZARD_AGILITY, WIZARD_INTELLIGENCE,
                WIZARD_HIT_POINTS, WIZARD_DAMAGE);
    }
}
