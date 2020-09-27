import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Integer>> usersMessages = new TreeMap<>();

        String input = scanner.nextLine();
        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");

            String adress = (tokens[0].split("="))[1];
            String message = (tokens[1].split("="))[1];
            String username = (tokens[2].split("="))[1];

            usersMessages.putIfAbsent(username, new LinkedHashMap<>());
            usersMessages.get(username).putIfAbsent(adress, 0);
            usersMessages.get(username).put(adress, usersMessages.get(username).get(adress) + 1);

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Map<String, Integer>> outerEntry : usersMessages.entrySet()) {
            System.out.println(outerEntry.getKey() + ": ");
            Map<String, Integer> innerMap = outerEntry.getValue();
            int counter = innerMap.size();
            for (Map.Entry<String, Integer> innerEntry : innerMap.entrySet()) {
                if (counter > 1) {
                    System.out.print(String.format("%s => %d, ", innerEntry.getKey(), innerEntry.getValue()));
                } else {
                    System.out.println(String.format("%s => %d.",innerEntry.getKey(), innerEntry.getValue()));
                }
                counter--;
            }
        }
    }
}
