import java.util.ArrayList;
import java.util.List;

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

    public void increaseNumberOfBadges() {
        this.numberOfBadges += 1;
    }

    public List<Pokemon> getPokemonList() {
        return this.pokemonList;
    }

    public void addToPokemonList(Pokemon pokemon) {
        this.pokemonList.add(pokemon);
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.numberOfBadges, this.pokemonList.size());
    }
}