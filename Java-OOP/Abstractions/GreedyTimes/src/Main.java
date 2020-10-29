import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] itemsInSafe = scanner.nextLine().split("\\s+");

        Map<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();

        long gold = 0;
        long gems = 0;
        long money = 0;

        for (int i = 0; i < itemsInSafe.length; i += 2) {

            String currItem = itemsInSafe[i];
            long currItemQuantity = Long.parseLong(itemsInSafe[i + 1]);

            String itemType = itemType(currItem);

            if (itemType.equals("")) {
                continue;
            } else if (bagCapacity < getBagCapacity(bag, currItemQuantity)) {
                continue;
            }

            switch (itemType) {
                case "Gem":
                    if (!bag.containsKey(itemType)) {
                        if (bag.containsKey("Gold")) {
                            if (currItemQuantity > getGoldQuantityInBag(bag, "Gold")) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (getGoldQuantityInBag(bag, itemType) + currItemQuantity > getGoldQuantityInBag(bag, "Gold")) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(itemType)) {
                        if (bag.containsKey("Gem")) {
                            if (currItemQuantity > getGoldQuantityInBag(bag, "Gold")) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (getGoldQuantityInBag(bag, itemType) + currItemQuantity > getGoldQuantityInBag(bag, "Gem")) {
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


            bag.get(itemType).put(currItem, bag.get(itemType).get(currItem) + currItemQuantity);

            if (itemType.equals("Gold")) {
                gold += currItemQuantity;
            } else if (itemType.equals("Gem")) {
                gems += currItemQuantity;
            } else if (itemType.equals("Cash")) {
                money += currItemQuantity;
            }
        }

        for (Map.Entry<String, LinkedHashMap<String, Long>> curr : bag.entrySet()) {
            Long sumValues = curr.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", curr.getKey(), sumValues));

            curr.getValue().entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
        }
    }

    private static long getGoldQuantityInBag(Map<String, LinkedHashMap<String, Long>> bag, String gold) {
        return bag.get(gold).values().stream().mapToLong(e -> e).sum();
    }

    private static long getBagCapacity(Map<String, LinkedHashMap<String, Long>> bag, long count) {
        return bag.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .mapToLong(e -> e).sum() + count;
    }

    private static String itemType(String currItem) {
        String itemType = "";

        if (currItem.length() == 3) {
            itemType = "Cash";
        } else if (currItem.toLowerCase().endsWith("gem")) {
            itemType = "Gem";
        } else if (currItem.toLowerCase().equals("gold")) {
            itemType = "Gold";
        }

        return itemType;
    }
}