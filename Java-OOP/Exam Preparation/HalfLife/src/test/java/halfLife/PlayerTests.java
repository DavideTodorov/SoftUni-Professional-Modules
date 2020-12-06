package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PlayerTests {
    private Player player;
    private Gun gun1;
    private Gun gun2;

    @Before
    public void setUp() {
        this.player = new Player("test_name", 100);
        this.gun1 = new Gun("gun_name1", 20);
        this.gun2 = new Gun("gun_name2", 30);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGunIfReturnsUnmodifiableList() {
        player.addGun(gun1);
        player.addGun(gun2);
        List<Gun> guns = player.getGuns();
        guns.remove(0);
    }

    @Test()
    public void testGetGunIfReturnsUCorrectList() {
        player.addGun(gun1);
        player.addGun(gun2);
        List<Gun> guns = player.getGuns();
        List<Gun> expected = Arrays.asList(gun1, gun2);
        Assert.assertEquals(expected.get(0).getName(),
                guns.get(0).getName());
        Assert.assertEquals(expected.get(1).getName(),
                guns.get(1).getName());
    }

    @Test
    public void testTakeDamage() {
        player.takeDamage(50);
        Assert.assertEquals(50, player.getHealth());
    }

    @Test
    public void testTakeMoreDamageThanHealth() {
        player.takeDamage(500);
        Assert.assertEquals(0, player.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageWithZeroHealth() {
        player.takeDamage(100);
        player.takeDamage(1);
    }

    @Test
    public void testAddValidGun() {
        player.addGun(gun1);
        List<Gun> guns = player.getGuns();
        Assert.assertEquals(1, guns.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullGun() {
        player.addGun(null);
    }

    @Test
    public void testGetGunByName() {
        player.addGun(gun1);
        player.addGun(gun2);

        Gun gun = player.getGun(gun1.getName());

        Assert.assertEquals(gun1.getName(), gun.getName());
    }

    @Test
    public void testRemoveGun() {
        player.addGun(gun1);
        player.addGun(gun2);
        boolean removeGun = player.removeGun(gun2);
        Assert.assertTrue(removeGun);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerWithNegativeHealth() {
        player = new Player("player", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testCreatePlayerWithNullName() {
        player = new Player(null, 100);
    }

    @Test
    public void testGetUsername(){
        String username = player.getUsername();
        Assert.assertEquals("test_name", username);
    }
}