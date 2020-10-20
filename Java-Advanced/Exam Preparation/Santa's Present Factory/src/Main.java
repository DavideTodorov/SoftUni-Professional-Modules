import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> materials = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> magicLevel = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));


        int dolls = 0;
        int woodenTrains = 0;
        int teddyBears = 0;
        int bicycles = 0;

        while (!materials.isEmpty() && !magicLevel.isEmpty()) {
            int currMaterial = materials.pollLast();
            int currMagicLevel = magicLevel.pollFirst();

            if (currMaterial == 0 && currMagicLevel == 0) {
                continue;
            }

            if (currMaterial == 0) {
                magicLevel.offerFirst(currMagicLevel);
                continue;
            }

            if (currMagicLevel == 0) {
                materials.offerLast(currMaterial);
                continue;
            }

            int result = currMaterial * currMagicLevel;

            if (result < 0) {
                result = currMaterial + currMagicLevel;
                materials.offerLast(result);
                continue;
            }

            if (result == 150) {
                dolls++;
            } else if (result == 250) {
                woodenTrains++;
            } else if (result == 300) {
                teddyBears++;
            } else if (result == 400) {
                bicycles++;
            } else if (result > 0) {
                currMaterial += 15;
                materials.offerLast(currMaterial);
            }
        }

        if (dolls > 0 && woodenTrains > 0) {
            System.out.println("The presents are crafted! Merry Christmas!");

        } else if (teddyBears > 0 && bicycles > 0) {
            System.out.println("The presents are crafted! Merry Christmas!");

        } else {
            System.out.println("No presents this Christmas!");

        }

        if (!materials.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            while (!materials.isEmpty()) {
                sb.append(String.valueOf(materials.pollLast())).append(", ");
            }

            System.out.printf("Materials left: %s%n", String.join(", ",
                    sb.toString().substring(0, sb.toString().lastIndexOf(", "))));

        } else if (!magicLevel.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            while (!magicLevel.isEmpty()) {
                sb.append(String.valueOf(magicLevel.pollFirst())).append(", ");
            }
            System.out.printf("Magic left: %s%n", String.join(", ",
                    sb.toString().substring(0, sb.toString().lastIndexOf(", "))));
        }

        if (bicycles > 0) {
            System.out.printf("Bicycle: %d%n", bicycles);
        }

        if (dolls > 0) {
            System.out.printf("Doll: %d%n", dolls);
        }

        if (teddyBears > 0) {
            System.out.printf("Teddy bear: %d%n", teddyBears);
        }

        if (woodenTrains > 0) {
            System.out.printf("Wooden rain: %d%n", woodenTrains);
        }
    }
}