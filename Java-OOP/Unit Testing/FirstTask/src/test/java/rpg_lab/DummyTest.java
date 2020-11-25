package rpg_lab;

import org.junit.*;
import static org.junit.Assert.*;

public class DummyTest {
    
    @Test
    public void testDummyLosesHealthAfterAttack() {
        Dummy dummy = new Dummy(1000, 10);
        dummy.takeAttack(100);
        int health = dummy.getHealth();
        assertEquals(900, health);
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackDeadDummy() {
        Dummy dummy = new Dummy(0, 10);
        dummy.takeAttack(100);
        assertEquals(0, dummy.getHealth());
    }

    @Test
    public void testDeadDummyGivesExperience() {
        Dummy dummy = new Dummy(0, 10);

        int xp = dummy.giveExperience();

        assertEquals(10, xp);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyGivesExperience(){
        Dummy dummy = new Dummy(10, 10);

        int xp = dummy.giveExperience();

        assertEquals(0,xp);
    }
}