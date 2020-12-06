package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setIsAlive(true);
        this.setGun(gun);
    }


    //Setters
    private void setIsAlive(boolean b) {
        this.isAlive = b;
    }

    private void setGun(Gun gun) {
        this.gun = gun;
    }

    private void setArmor(int armor) {
        this.armor = armor;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    private void setUsername(String username) {
        this.username = username;
    }


    //Methods
    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public Gun getGun() {
        return null;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void takeDamage(int points) {

    }
}
