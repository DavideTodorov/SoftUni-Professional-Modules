package rpg_lab;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;

public class HeroTest {

    @Test
    public void testHeroGainsExperienceWhenKillsATarget() {
        Target target = Mockito.mock(Target.class);

        Weapon weapon = Mockito.mock(Weapon.class);

        Hero hero = new Hero("Test_Hero", weapon);

        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveExperience()).thenReturn(10);

        hero.attack(target);
        int experience = hero.getExperience();
        assertEquals(10, experience);
    }
}