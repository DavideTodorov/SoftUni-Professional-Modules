import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().split("\\s+");
        }

        int carrots = 0;
        int potatoes = 0;
        int lettuce = 0;
        int harmedVegetables = 0;

        String input = scanner.nextLine();
        while (!"End of Harvest".equals(input)) {
            String[] tokens = input.split("\\s+");

            String command = tokens[0];
            int row = Integer.parseInt(tokens[1]);
            int col = Integer.parseInt(tokens[2]);

            switch (command) {
                case "Harvest":
                    String vegetableType = harvest(row, col, matrix);
                    switch (vegetableType) {
                        case "C":
                            carrots++;
                            break;

                        case "P":
                            potatoes++;
                            break;

                        case "L":
                            lettuce++;
                            break;
                    }
                    break;

                case "Mole":
                    String direction = tokens[3];
                    harmedVegetables = mole(row, col, direction
                            , matrix, harmedVegetables);
                    break;
            }

            input = scanner.nextLine();
        }

        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");

            }
            System.out.println();
        }

        System.out.printf("Carrots: %d\n" +
                "Potatoes: %d\n" +
                "Lettuce: %d\n" +
                "Harmed vegetables: %d\n", carrots, potatoes, lettuce, harmedVegetables);
    }


    private static int mole(int row, int col, String direction, String[][] matrix, int harmedVegetables) {
        if (direction.equalsIgnoreCase("Up")) {
            if (indexIsInBounds(row, col, matrix)) {
                while (indexIsInBounds(row, col, matrix)) {

                    if (matrix[row][col].equalsIgnoreCase(" ")) {
                        harmedVegetables++;
                    }

                    matrix[row][col] = " ";
                    row -= 2;
                }
            }

        } else if (direction.equalsIgnoreCase("Down")) {
            if (indexIsInBounds(row, col, matrix)) {
                while (indexIsInBounds(row, col, matrix)) {
                    matrix[row][col] = " ";
                    row += 2;
                    harmedVegetables++;
                }
            }

        } else if (direction.equalsIgnoreCase("Left")) {
            if (indexIsInBounds(row, col, matrix)) {
                while (indexIsInBounds(row, col, matrix)) {
                    matrix[row][col] = " ";
                    col -= 2;
                    harmedVegetables++;
                }
            }

        } else if (direction.equalsIgnoreCase("Right")) {
            if (indexIsInBounds(row, col, matrix)) {
                while (indexIsInBounds(row, col, matrix)) {
                    matrix[row][col] = " ";
                    col += 2;
                    harmedVegetables++;
                }
            }

        }
        return harmedVegetables;
    }

    private static String harvest(int row, int col, String[][] matrix) {
        String vegetableType = "";
        if (indexIsInBounds(row, col, matrix)) {
            vegetableType = matrix[row][col];
            matrix[row][col] = " ";
        }
        return vegetableType;
    }

    private static boolean indexIsInBounds(int row, int col, String[][] matrix) {
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[row].length;
    }
}