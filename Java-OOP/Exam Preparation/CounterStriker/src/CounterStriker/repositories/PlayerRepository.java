package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.*;

import static CounterStriker.common.ExceptionMessages.INVALID_PLAYER_REPOSITORY;

public class PlayerRepository implements Repository<Player> {
    private Map<String, Player> models;

    public PlayerRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public void add(Player player) {
        if (player == null) {
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }

        models.put(player.getUsername(), player);
    }

    @Override
    public boolean remove(Player player) {
        return models.remove(player.getUsername(), player);
    }

    @Override
    public Player findByName(String name) {
        Player player = null;

        for (Player currPlayer : models.values()) {
            if (currPlayer.getUsername().equals(name)) {
                player = currPlayer;
                break;
            }
        }

        return player;
    }

    @Override
    public Collection<Player> getModels() {
        return new ArrayList<>(models.values());
    }
}
