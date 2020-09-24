import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sizeInput = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int rows = sizeInput[0];
        int cols = sizeInput[1];

        String[][] matrix = new String[rows][];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scanner.nextLine().split("\\s+");
        }

        int row = matrix.length - 1;
        int col = matrix[row].length - 1;

        StringBuilder result = new StringBuilder();

        while (col >= 0) {
            result.append(matrix[row][col]);
            result.append(" ");

            int diagonalRow = row - 1;
            int diagonalCol = col + 1;

            while (diagonalRow >= 0 && diagonalCol < matrix[diagonalRow].length) {
                result.append(matrix[diagonalRow][diagonalCol]);
                result.append(" ");

                diagonalRow--;
                diagonalCol++;
            }

            col--;
            result.append("\n");
        }

        row = row - 1;
        col = 0;

        while (row >= 0) {
            result.append(matrix[row][col]);
            result.append(" ");

            int diagonalRow = row - 1;
            int diagonalCol = col + 1;

            while (diagonalRow >= 0 && diagonalCol < matrix[diagonalRow].length) {
                result.append(matrix[diagonalRow][diagonalCol]);
                result.append(" ");
                diagonalRow--;
                diagonalCol++;
            }

            row--;
            result.append("\n");
        }

        System.out.println(result);
    }
}
