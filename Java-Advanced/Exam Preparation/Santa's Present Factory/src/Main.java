import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> materialsStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(materialsStack::push);

        ArrayDeque<Integer> magicLevelQueue = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));


        Map<String, Integer> toys = new TreeMap<>();

        toys.put("Doll", 0);
        toys.put("Wooden train", 0);
        toys.put("Teddy bear", 0);
        toys.put("Bicycle", 0);


        while (!materialsStack.isEmpty() && !magicLevelQueue.isEmpty()) {
            int currMaterial = materialsStack.pop();
            int currMagicLevel = magicLevelQueue.poll();

            int result = currMaterial * currMagicLevel;

            if (result < 0) {
                result = currMaterial + currMagicLevel;
                materialsStack.push(result);
                continue;
            }

            if (currMaterial == 0 && currMagicLevel == 0) {
                continue;
            }

            if (currMaterial == 0) {
                magicLevelQueue.offerFirst(currMagicLevel);
                continue;
            }

            if (currMagicLevel == 0) {
                materialsStack.push(currMaterial);
                continue;
            }

            if (result == 150) {
                toys.put("Doll", toys.get("Doll") + 1);

            } else if (result == 250) {
                toys.put("Wooden train", toys.get("Wooden train") + 1);

            } else if (result == 300) {
                toys.put("Teddy bear", toys.get("Teddy bear") + 1);

            } else if (result == 400) {
                toys.put("Bicycle", toys.get("Bicycle") + 1);

            } else if (result > 0) {
                currMaterial += 15;
                materialsStack.push(currMaterial);
            }
        }

        if (toys.get("Doll") > 0 && toys.get("Wooden train") > 0) {
            System.out.println("The presents are crafted! Merry Christmas!");

        } else if (toys.get("Teddy bear") > 0 && toys.get("Bicycle") > 0) {
            System.out.println("The presents are crafted! Merry Christmas!");

        } else {
            System.out.println("No presents this Christmas!");

        }

        if (!materialsStack.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            while (!materialsStack.isEmpty()) {
                sb.append(String.valueOf(materialsStack.pop())).append(", ");
            }

            System.out.printf("Materials left: %s%n", String.join(", ",
                    sb.toString().substring(0, sb.toString().lastIndexOf(", "))));

        }

        if (!magicLevelQueue.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            while (!magicLevelQueue.isEmpty()) {
                sb.append(String.valueOf(magicLevelQueue.poll())).append(", ");
            }
            System.out.printf("Magic left: %s%n", String.join(", ",
                    sb.toString().substring(0, sb.toString().lastIndexOf(", "))));
        }

        toys.entrySet()
                .stream()
                .filter(t -> t.getValue() > 0)
                .forEach(t -> System.out.printf("%s: %d%n", t.getKey(), t.getValue()));
    }
}