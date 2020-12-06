package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import static CounterStriker.common.ExceptionMessages.*;
import static CounterStriker.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;

        if (type.equals("Pistol")) {
            gun = new Pistol(name, bulletsCount);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }

        guns.add(gun);

        return String.format(SUCCESSFULLY_ADDED_GUN, gun.getName());
    }

    @Override
    public String addPlayer(String type, String username, int health,
                            int armor, String gunName) {
        Player player;

        Gun gun = guns.findByName(gunName);

        if (gun == null) {
            throw new NullPointerException(GUN_CANNOT_BE_FOUND);
        }

        if (type.equals("Terrorist")) {
            player = new Terrorist(username, health, armor, gun);
        }else if (type.equals("CounterTerrorist")){
            player = new CounterTerrorist(username, health, armor, gun);
        }else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        players.add(player);

        return String.format(SUCCESSFULLY_ADDED_PLAYER,player.getUsername());
    }

    @Override
    public String startGame() {
        return null;
    }

    @Override
    public String report() {
        return null;
    }
}
