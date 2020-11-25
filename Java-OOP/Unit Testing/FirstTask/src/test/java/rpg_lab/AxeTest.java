package rpg_lab;

import org.junit.*;

import static org.junit.Assert.*;

public class AxeTest {

    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp(){
        this.axe = new Axe(10,10);
        this.dummy = new Dummy(1000, 10);
    }

    @Test
    public void testWeaponLosesDurabilityAfterAttack() {
        axe.attack(dummy);
        int durabilityPoints = axe.getDurabilityPoints();
        assertEquals(9, durabilityPoints);
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackingWithBrokenWeapon() {
        Axe axe = new Axe(10, 0);

        axe.attack(dummy);
        int durabilityPoints = axe.getDurabilityPoints();
        assertEquals(9, durabilityPoints);
    }
}