package CounterStriker.repositories;

import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerRepository implements Repository<Player> {
    private Map<String, Player> models;

    public PlayerRepository() {
        this.models = new HashMap<>();
    }

    @Override
    public Collection<Player> getModels() {
        return null;
    }

    @Override
    public void add(Player model) {

    }

    @Override
    public boolean remove(Player model) {
        return false;
    }

    @Override
    public Player findByName(String name) {
        return null;
    }
}
