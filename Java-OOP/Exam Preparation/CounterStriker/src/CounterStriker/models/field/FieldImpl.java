package CounterStriker.models.field;

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {
        List<Player> playerList = new ArrayList<>(players);

        List<Player> terrorists = new ArrayList<>();
        List<Player> counterTerrorists = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getClass().getSimpleName().equals("Terrorist")) {
                terrorists.add(player);
            } else {
                counterTerrorists.add(player);
            }
        }

        boolean hasWinner = false;

        while (!hasWinner) {
            //TERRORISTS ATTACK
            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    counterTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }

            boolean cTAreAlive = false;
            for (Player counterTerrorist : counterTerrorists) {
                if (counterTerrorist.isAlive()) {
                    cTAreAlive = true;
                    break;
                }
            }

            if (!cTAreAlive) {
                return "Terrorist wins!";
            }


            //COUNTER TERRORISTS ATTACK
            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(counterTerrorist.getGun().fire());
                }
            }


            boolean tAreAlive = false;
            for (Player terrorist : terrorists) {
                if (terrorist.isAlive()) {
                    tAreAlive = true;
                    break;
                }
            }

            if (!tAreAlive) {
                return "Counter Terrorist wins!";
            }
        }

        return null;
    }
}
