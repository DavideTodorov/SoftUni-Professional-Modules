import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int commandsCount = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().split("");
        }

        int playerRow = 0;
        int playerCol = 0;
        boolean isFound = false;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("f")) {
                    playerRow = row;
                    playerCol = col;
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }

        for (int i = 0; i < commandsCount; i++) {
            String input = scanner.nextLine();

            switch (input) {
                case "up":
                    matrix[playerRow][playerCol] = "-";

                    playerRow = playerRowUp(matrix, playerRow);

                    if (matrix[playerRow][playerCol].equals("B")) {
                        while (matrix[playerRow][playerCol].equals("B")) {
                            playerRow = playerRowUp(matrix, playerRow);
                        }

                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        while (matrix[playerRow][playerCol].equals("T")) {
                            playerRow = playerRowDown(matrix, playerRow);
                        }

                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        printIfWin(matrix);
                        return;
                    }
                    matrix[playerRow][playerCol] = "f";
                    break;

                case "down":
                    matrix[playerRow][playerCol] = "-";

                    playerRow = playerRowDown(matrix, playerRow);

                    if (matrix[playerRow][playerCol].equals("B")) {
                        while (matrix[playerRow][playerCol].equals("B")) {
                            playerRow = playerRowDown(matrix, playerRow);
                        }

                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        while (matrix[playerRow][playerCol].equals("T")) {
                            playerRow = playerRowUp(matrix, playerRow);
                        }

                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        printIfWin(matrix);
                        return;
                    }
                    matrix[playerRow][playerCol] = "f";
                    break;

                case "right":
                    matrix[playerRow][playerCol] = "-";

                    playerCol = playerColRight(matrix, playerCol, playerRow);

                    if (matrix[playerRow][playerCol].equals("B")) {
                        while (matrix[playerRow][playerCol].equals("B")) {
                            playerCol = playerColRight(matrix, playerCol, playerRow);
                        }


                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        while (matrix[playerRow][playerCol].equals("T")) {
                            playerCol = playerColLeft(matrix, playerCol, playerCol);
                        }


                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        printIfWin(matrix);
                        return;
                    }
                    matrix[playerRow][playerCol] = "f";
                    break;

                case "left":
                    matrix[playerRow][playerCol] = "-";

                    playerCol = playerColLeft(matrix, playerCol, playerRow);

                    if (matrix[playerRow][playerCol].equals("B")) {
                        while (matrix[playerRow][playerCol].equals("B")) {
                            playerCol = playerColLeft(matrix, playerCol, playerRow);
                        }


                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        while (matrix[playerRow][playerCol].equals("T")) {
                            playerCol = playerColRight(matrix, playerCol, playerCol);
                        }


                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        printIfWin(matrix);
                        return;
                    }
                    matrix[playerRow][playerCol] = "f";
                    break;
            }
        }

        System.out.println("Player lost!");
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static int playerColRight(String[][] matrix, int playerCol, int playerRow) {
        playerCol++;

        if (playerCol >= matrix[playerRow].length) {
            playerCol = 0;
        }

        return playerCol;
    }

    private static int playerColLeft(String[][] matrix, int playerCol, int playerRow) {
        playerCol--;

        if (playerCol < 0) {
            playerCol = matrix[playerRow].length - 1;
        }

        return playerCol;
    }

    private static void printIfWin(String[][] matrix) {
        System.out.println("Player won!");
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static int playerRowDown(String[][] matrix, int playerRow) {
        playerRow++;
        if (playerRow >= matrix.length) {
            playerRow = 0;
        }
        return playerRow;
    }

    private static int playerRowUp(String[][] matrix, int playerRow) {
        playerRow--;
        if (playerRow < 0) {
            playerRow = matrix.length - 1;
        }
        return playerRow;
    }
}