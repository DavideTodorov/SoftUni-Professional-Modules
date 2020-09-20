import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rolls = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        char[][] firstMatrix = readCharMatrix(scanner, rolls, cols);
        char[][] secondMatrix = readCharMatrix(scanner, rolls, cols);
        char[][] finalMatrix = new char[rolls][cols];

        for (int rol = 0; rol < rolls; rol++) {
            for (int col = 0; col < cols; col++) {
                if (firstMatrix[rol][col] == secondMatrix[rol][col]) {
                    finalMatrix[rol][col] = firstMatrix[rol][col];
                } else {
                    finalMatrix[rol][col] = '*';
                }
            }
        }

        for (char[] matrix : finalMatrix) {
            for (char c : matrix) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static char[][] readCharMatrix(Scanner scanner, int rolls, int cols) {
        char[][] matrix = new char[rolls][cols];
        for (int i = 0; i < rolls; i++) {
            String[] elements = scanner.nextLine().split("\\s+");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = elements[j].charAt(0);
            }
        }
        return matrix;
    }

    private static int[][] readIntArr(Scanner scanner, int rolls) {
        int[][] matrix = new int[rolls][];
        for (int rol = 0; rol < rolls; rol++) {
            matrix[rol] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
