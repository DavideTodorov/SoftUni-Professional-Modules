import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Buyer> buyers = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                //CITIZEN
                buyers.putIfAbsent(tokens[0],
                        new Citizen(tokens[0],
                                Integer.parseInt(tokens[1]),
                                tokens[2], tokens[3]));

            } else if (tokens.length == 3) {
                //REBEl
                buyers.putIfAbsent(tokens[0], new Rebel(tokens[0],
                        Integer.parseInt(tokens[1]),
                        tokens[2]));
            }
        }

        String nameInput = scanner.nextLine();

        while (!"End".equalsIgnoreCase(nameInput)) {
            if (buyers.containsKey(nameInput)) {
                buyers.get(nameInput).buyFood();
            }

            nameInput = scanner.nextLine();
        }

        int foodBought = 0;
        for (Buyer buyer : buyers.values()) {
            foodBought += buyer.getFood();
        }

        System.out.println(foodBought);
    }
}
