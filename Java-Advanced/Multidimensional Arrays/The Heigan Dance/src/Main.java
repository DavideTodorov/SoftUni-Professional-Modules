import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int playerRow = 7;
        int playerCol = 7;

        double heiganHealth = 3000000.0;
        int playerHealth = 18500;

        double playerDamage = Double.parseDouble(scanner.nextLine());

        String lastSpell = "";
        while (true) {

            if (playerHealth >= 0) {
                heiganHealth -= playerDamage;
            }

            if (lastSpell.equals("Plague Cloud")) {
                playerHealth -= 3500;
            }

            if (playerHealth <= 0 && heiganHealth <= 0) {
                System.out.printf("Heigan: Defeated!%n", heiganHealth);
                System.out.printf("Player: Killed by %s%n", lastSpell);
                System.out.printf("Final position: %d, %d%n", playerRow, playerCol);
                return;
            }

            if (playerHealth <= 0) {
                System.out.printf("Heigan: %.2f%n", heiganHealth);
                System.out.printf("Player: Killed by %s%n", lastSpell);
                System.out.printf("Final position: %d, %d%n", playerRow, playerCol);
                return;
            }

            if (heiganHealth <= 0) {
                System.out.printf("Heigan: Defeated!%n", heiganHealth);
                System.out.printf("Player: %d%n", playerHealth);
                System.out.printf("Final position: %d, %d%n", playerRow, playerCol);
                return;
            }

            String[] input = scanner.nextLine().split("\\s+");
            String spell = input[0];
            int spellRow = Integer.parseInt(input[1]);
            int spellCol = Integer.parseInt(input[2]);


            if (isInDamageArea(spellRow, spellCol, playerRow, playerCol)) {
                if (!isInDamageArea(spellRow, spellCol, playerRow - 1, playerCol) &&
                        !isWall(playerRow - 1)) {
                    playerRow--;
                    lastSpell = "";
                } else if (!isInDamageArea(spellRow, spellCol, playerRow, playerCol + 1) &&
                        !isWall(playerCol + 1)) {
                    playerCol++;
                    lastSpell = "";
                } else if (!isInDamageArea(spellRow, spellCol, playerRow + 1, playerCol) &&
                        !isWall(playerRow + 1)) {
                    playerRow++;
                    lastSpell = "";
                } else if (!isInDamageArea(spellRow, spellCol, playerRow, playerCol - 1) &&
                        !isWall(playerCol - 1)) {
                    playerCol--;
                    lastSpell = "";
                } else {
                    if (spell.equals("Cloud")) {
                        playerHealth -= 3500;
                        lastSpell = "Plague Cloud";
                    } else if (spell.equals("Eruption")) {
                        playerHealth -= 6000;
                        lastSpell = "Eruption";
                    }
                }
            }
        }
    }

    private static boolean isWall(int i) {
        return i < 0 || i >= 15;
    }

    private static boolean isInDamageArea(int spellRow, int spellCol, int playerRow, int playerCol) {
        return (spellRow - 1 <= playerRow && playerRow <= spellRow + 1) &&
                (spellCol - 1 <= playerCol && playerCol <= spellCol + 1);
    }
}
