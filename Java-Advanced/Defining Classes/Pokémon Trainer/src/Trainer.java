import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trainer {

    private String name;
    private int numberOfBadges;
    private List<Pokemon> pokemonList;

    public Trainer(String name, Pokemon pokemon) {
        this.name = name;
        this.numberOfBadges = 0;
        this.pokemonList = new ArrayList<>();
        this.pokemonList.add(pokemon);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBadges() {
        return this.numberOfBadges;
    }

    public void setNumberOfBadges(int numberOfBadges) {
        this.numberOfBadges = numberOfBadges;
    }

    public List<Pokemon> getPokemonList() {
        return this.pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
