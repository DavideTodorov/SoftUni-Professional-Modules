package guild;

import java.util.ArrayList;
import java.util.LinkedHashMap;;
import java.util.List;
import java.util.Map;


public class Guild {

    private String name;
    private int capacity;
    private List<Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.capacity > roster.size()) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        return this.roster.removeIf(player -> player.getName().equals(name));
    }

    public void promotePlayer(String name) {
        for (Player player : roster) {
            if (player.getName().equals(name)) {
                player.setRank("Member");
                break;
            }
        }
    }

    public void demotePlayer(String name) {
        for (Player player : roster) {
            if (player.getName().equals(name)) {
                player.setRank("Trial");
            }
        }
    }

    public int count() {
        return this.roster.size();
    }

    public Player[] kickPlayersByClass(String clazz) {
        Player[] players =
                this.roster.stream()
                        .filter(player -> player.getClazz().equals(clazz))
                        .toArray(Player[]::new);
        for (Player player : players) {
            this.roster.removeIf((player1 -> player1.getName().equals(player.getName())));
        }
        return players;
    }

    public String report() {
        StringBuilder print = new StringBuilder();
        print.append("Players in the guild: ")
                .append(this.name)
                .append(":")
                .append(System.lineSeparator());
        for (Player player : roster) {
            print.append(player.toString())
                    .append(System.lineSeparator());
        }

        return print.toString().trim();
    }
}
