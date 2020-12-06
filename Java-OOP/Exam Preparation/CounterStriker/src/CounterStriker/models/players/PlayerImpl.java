package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import javax.print.DocFlavor;

import static CounterStriker.common.ExceptionMessages.*;

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
        this.setIsAlive();
        this.setGun(gun);
    }


    //Setters
    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    private void setIsAlive() {
        if (health > 0) {
            this.isAlive = true;
        } else {
            this.isAlive = false;
        }
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }


    //Methods
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {
        if (isAlive) {
            int currArmor = this.getArmor();
            currArmor -= points;

            int currHealth = this.getHealth();

            if (currArmor < 0) {
                currHealth -= currArmor;
                this.setArmor(0);
            } else {
                this.setArmor(currArmor);
                return;
            }

            if (currHealth <= 0) {
                this.setHealth(0);
                this.setIsAlive();
            } else {
                this.setHealth(currHealth);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s: %s", super.getClass().getSimpleName(), this.getUsername()))
                .append(System.lineSeparator());

        builder.append(String.format("--Health: %d", this.getHealth())).append(System.lineSeparator());
        builder.append(String.format("--Armor: %d", this.getArmor())).append(System.lineSeparator());
        builder.append(String.format("--Gun: %s", this.getGun().getName()))
                .append(System.lineSeparator());

        return builder.toString().trim();
    }
}
