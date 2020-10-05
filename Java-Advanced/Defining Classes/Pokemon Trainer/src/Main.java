import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Trainer> trainers = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"Tournament".equals(input)) {
            String[] tokens = input.split("\\s+");

            String trainerName = tokens[0];

            if (trainers.contains(trainerName)) {
                input = scanner.nextLine();
                continue;
            }

            String pokemonName = tokens[1];

            boolean pokemonExists = false;
            for (Trainer trainer : trainers) {
                if (trainer.getPokemonList().contains(pokemonName)) {
                    pokemonExists = true;
                    break;
                }
            }

            if (pokemonExists) {
                input = scanner.nextLine();
                continue;
            }

            String pokemonElement = tokens[2];
            int pokemonHealth = Integer.parseInt(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            Trainer trainer = new Trainer(trainerName, pokemon);

            trainers.add(trainer);

            input = scanner.nextLine();
        }

        System.out.println();
    }
}