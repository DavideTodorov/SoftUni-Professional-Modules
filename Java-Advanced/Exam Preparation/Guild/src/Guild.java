import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Guild {

    private String name;
    private int capacity;
    private Map<String, Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new LinkedHashMap<>();
    }

    public void addPlayer(Player player) {
        if (this.roster.size() < capacity) {
            this.roster.put(player.getName(), player);
        }
    }

    public boolean removePlayer(String name) {
        boolean isRemoved = false;
        if (this.roster.containsKey(name)) {
            this.roster.remove(name);
            isRemoved = true;
        }
        return isRemoved;
    }

    public void promotePlayer(String name) {
        if (this.roster.containsKey(name)) {
            this.roster.get(name).setRank("Member");
        }
    }

    public void demotePlayer(String name) {
        if (this.roster.containsKey(name)) {
            this.roster.get(name).setRank("Trial");
        }
    }

    public Player[] kickPlayersByClass(String clazz) {
        Player[] playersFromClazz = roster.values().stream()
                .filter(s -> s.getClazz().equals(clazz))
                .toArray(Player[]::new);

        for (Player currPlayer : playersFromClazz) {
            this.roster.remove(currPlayer.getName());
        }

        return playersFromClazz;
    }


    public int count() {
        return this.roster.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Players in the guild: %s:", this.name)).append(System.lineSeparator());

        int countIterations = 0;
        for (Player value : roster.values()) {
            if (countIterations == roster.size() - 1) {
                sb.append(value);
            } else {
                sb.append(value).append(System.lineSeparator());
            }
            countIterations++;
        }

        return sb.toString();
    }
}
