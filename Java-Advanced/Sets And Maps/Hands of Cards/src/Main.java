import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, LinkedHashSet<String>> peoplesDecks = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!"JOKER".equals(input)) {
            String[] tokens = input.split(":\\s+");
            String name = tokens[0];
            String[] cards = tokens[1].split(",\\s+");

            peoplesDecks.putIfAbsent(name, new LinkedHashSet<>());

            for (int i = 0; i < cards.length; i++) {
                String currCard = cards[i];
                peoplesDecks.get(name).add(currCard);
            }

            input = scanner.nextLine();
        }

        Map<String, Integer> decksValues = new LinkedHashMap<>();

        for (Map.Entry<String, LinkedHashSet<String>> stringLinkedHashSetEntry : peoplesDecks.entrySet()) {
            String name = stringLinkedHashSetEntry.getKey();
            Set<String> hashSet = stringLinkedHashSetEntry.getValue();

            int result = 0;
            for (String s : hashSet) {
                String power = s.substring(0, s.length() - 1);
                String multiplier = s.substring(s.length() - 1);

                int powerValue = Integer.parseInt(returnPowerValue(power));
                int multiplierValue = Integer.parseInt(returnMultiplier(multiplier));

                result = result + (powerValue * multiplierValue);
            }

            decksValues.put(name, result);
        }

        decksValues.entrySet()
                .forEach(p -> {
                    System.out.printf("%s: %d%n", p.getKey(), p.getValue());
                });
    }

    private static int calculateValue(String[] cards) {
        int value = 0;
        for (int i = 0; i < cards.length; i++) {
            String[] currCard = cards[i].split("");
            String power = currCard[0];
            String multiplier = currCard[1];

            int powerInNumbers = Integer.parseInt(returnPowerValue(power));
            int multiplierInNumbers = Integer.parseInt(returnMultiplier(multiplier));
            value = value + (powerInNumbers * multiplierInNumbers);
        }
        return value;
    }

    private static String returnMultiplier(String multiplier) {
        switch (multiplier) {
            case "S":
                multiplier = "4";
                break;

            case "H":
                multiplier = "3";
                break;

            case "D":
                multiplier = "2";
                break;

            case "C":
                multiplier = "1";
                break;
        }

        return multiplier;
    }

    private static String returnPowerValue(String power) {
        switch (power) {
            case "J":
                power = "11";
                break;

            case "Q":
                power = "12";
                break;

            case "K":
                power = "13";
                break;

            case "A":
                power = "14";
                break;
        }

        return power;
    }
}
