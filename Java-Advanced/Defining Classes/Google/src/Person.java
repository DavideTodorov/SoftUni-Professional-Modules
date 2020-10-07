import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;

    //only one
    private Company company;
    private Car car;

    //one or more
    private List<Parent> parents;
    private List<Child> children;
    private List<Pokemon> pokemons;

    public Person(String name) {
        this.name = name;
        this.company = new Company("", "", 0.0);
        this.car = new Car("", 0);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void addParent(Parent parent) {
        try {
            this.parents.add(parent);
        } catch (NullPointerException e) {
            this.parents = new ArrayList<>();
            addParent(parent);
        }
    }

    public void addChild(Child child) {
        try {
            this.children.add(child);
        } catch (NullPointerException e) {
            this.children = new ArrayList<>();
            addChild(child);
        }
    }

    public void addPokemon(Pokemon pokemon) {
        try {
            this.pokemons.add(pokemon);
        } catch (NullPointerException e) {
            this.pokemons = new ArrayList<>();
            addPokemon(pokemon);
        }
    }

    @Override
    public String toString() {
        StringBuilder pokemonsToPrint = new StringBuilder();
        try {
            if (pokemons.isEmpty()) {
                pokemonsToPrint.append("");
            } else {
                for (Pokemon pokemon : this.pokemons) {
                    pokemonsToPrint.append(pokemon.toString()).append("\n");
                }
            }
        } catch (NullPointerException e) {
            pokemonsToPrint.append("");
        }

        StringBuilder parentsToPrint = new StringBuilder();
        try {
            if (parents.isEmpty()) {
                parentsToPrint.append("");
            } else {
                for (Parent parent : this.parents) {
                    parentsToPrint.append(parent.toString()).append("\n");
                }
            }
        } catch (NullPointerException e) {
            parentsToPrint.append("");
        }

        StringBuilder childrenToPrint = new StringBuilder();
        try {
            if (children.isEmpty()) {
                childrenToPrint.append("");
            } else {
                for (Child child : this.children) {
                    childrenToPrint.append(child.toString()).append("\n");
                }
            }
        } catch (NullPointerException e) {
            childrenToPrint.append("");
        }

        return String.format("%s\n" +
                        "Company:\n%s" +
                        "Car:\n%s" +
                        "Pokemon:\n%s" +
                        "Parents:\n%s" +
                        "Children:\n%s", this.name, this.company.toString(), this.car.toString(),
                pokemonsToPrint.toString(), parentsToPrint.toString(), childrenToPrint.toString())
                ;
    }
}