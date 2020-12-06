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
            if (player.getClass().getSimpleName().equals("Terrorist")){
                terrorists.add(player);
            }else {
                counterTerrorists.add(player);
            }
        }

        



        return null;
    }
}
