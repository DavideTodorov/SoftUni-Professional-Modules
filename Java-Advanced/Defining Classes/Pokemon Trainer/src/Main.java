import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Trainer> trainers = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!"Tournament".equals(input)) {
            String[] tokens = input.split("\\s+");

            String trainerName = tokens[0];

            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            int pokemonHealth = Integer.parseInt(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);

            if (trainers.containsKey(trainerName)) {
                trainers.get(trainerName).addToPokemonList(pokemon);
            } else {
                Trainer trainer = new Trainer(trainerName, pokemon);
                trainers.put(trainerName, trainer);
            }

            input = scanner.nextLine();
        }


        String element = scanner.nextLine();
        while (!"End".equals(element)) {

            for (Map.Entry<String, Trainer> trainer : trainers.entrySet()) {

                checkForElement(element, trainer.getValue());

            }

            element = scanner.nextLine();
        }

        trainers.entrySet().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getValue().getNumberOfBadges(),
                        t1.getValue().getNumberOfBadges()))
                .forEach(t -> System.out.println(t.getValue().toString()));
    }

    private static void checkForElement(String element, Trainer trainer) {
        List<Pokemon> pokemonList = trainer.getPokemonList();
        boolean containsElement = false;

        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getElement().equals(element)) {
                trainer.increaseNumberOfBadges();
                containsElement = true;
                break;
            }
        }

        if (!containsElement) {
            for (Pokemon pokemon : pokemonList) {
                pokemon.decreaseHealthBy10();
                if (pokemon.getHealth() <= 0) {
                    trainer.getPokemonList().remove(pokemon);
                    if (trainer.getPokemonList().size() == 0) {
                        return;
                    }
                }
            }
        }
    }
}