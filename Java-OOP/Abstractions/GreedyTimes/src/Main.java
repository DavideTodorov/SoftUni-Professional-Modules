import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int bagCapacity = Integer.parseInt(scanner.nextLine());
        String[] itemsInSafe = scanner.nextLine().split("\\s+");

        Map<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();

        long gold = 0;
        long gems = 0;
        long money = 0;

        for (int i = 0; i < itemsInSafe.length; i += 2) {

            String currItem = itemsInSafe[i];
            long count = Long.parseLong(itemsInSafe[i + 1]);

            String itemType = "";

            if (currItem.length() == 3) {
                itemType = "Cash";
            } else if (currItem.toLowerCase().endsWith("gem")) {
                itemType = "Gem";
            } else if (currItem.toLowerCase().equals("gold")) {
                itemType = "Gold";
            }

            if (itemType.equals("")) {
                continue;
            } else if (bagCapacity < bag.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + count) {
                continue;
            }

            switch (itemType) {
                case "Gem":
                    if (!bag.containsKey(itemType)) {
                        if (bag.containsKey("Gold")) {
                            if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(itemType).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(itemType)) {
                        if (bag.containsKey("Gem")) {
                            if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(itemType).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!bag.containsKey(itemType)) {
                bag.put((itemType), new LinkedHashMap<String, Long>());
            }

            if (!bag.get(itemType).containsKey(currItem)) {
                bag.get(itemType).put(currItem, 0L);
            }


            bag.get(itemType).put(currItem, bag.get(itemType).get(currItem) + count);
            
            if (itemType.equals("Gold")) {
                gold += count;
            } else if (itemType.equals("Gem")) {
                gems += count;
            } else if (itemType.equals("Cash")) {
                money += count;
            }
        }

        for (var x : bag.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}