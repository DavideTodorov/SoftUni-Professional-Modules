import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int wormRow = 0;
    public static int wormCol = 0;
    public static StringBuilder word = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String initialWord = scanner.nextLine();

        word.append(initialWord);

        int matrixSize = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[matrixSize][matrixSize];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 'P') {
                    wormRow = row;
                    wormCol = col;
                    break;
                }
            }
        }

        String input = scanner.nextLine();
        while (!"end".equals(input)) {

            if ("up".equals(input)) {
                //row - 1
                if (isInBounds(wormRow - 1, wormCol, matrix)) {
                    matrix[wormRow][wormCol] = '-';

                    moveWorm(wormRow - 1, wormCol, matrix);

                    wormRow--;
                } else {
                    removeLastLetter();
                }

            } else if ("down".equals(input)) {
                //row + 1
                if (isInBounds(wormRow + 1, wormCol, matrix)) {
                    matrix[wormRow][wormCol] = '-';

                    moveWorm(wormRow + 1, wormCol, matrix);

                    wormRow++;
                } else {
                    removeLastLetter();
                }

            } else if ("right".equals(input)) {
                //col + 1
                if (isInBounds(wormRow, wormCol + 1, matrix)) {
                    matrix[wormRow][wormCol] = '-';

                    moveWorm(wormRow, wormCol + 1, matrix);

                    wormCol++;
                } else {
                    removeLastLetter();
                }

            } else if ("left".equals(input)) {
                //col - 1
                if (isInBounds(wormRow, wormCol - 1, matrix)) {
                    matrix[wormRow][wormCol] = '-';

                    moveWorm(wormRow, wormCol - 1, matrix);

                    wormCol--;
                } else {
                    removeLastLetter();
                }
            }

            input = scanner.nextLine();
        }

        System.out.println(word.toString());

        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static void moveWorm(int wormRow, int wormCol, char[][] matrix) {
        char element = matrix[wormRow][wormCol];

        if (Character.isAlphabetic(element)) {
            word.append(element);
        }

        matrix[wormRow][wormCol] = 'P';
    }

    private static void removeLastLetter() {
        word.delete(word.length() - 1, word.length());
    }

    private static boolean isInBounds(int wormRow, int wormCol, char[][] matrix) {
        return wormRow >= 0 && wormRow < matrix.length &&
                wormCol >= 0 && wormCol < matrix[wormRow].length;
    }
}
