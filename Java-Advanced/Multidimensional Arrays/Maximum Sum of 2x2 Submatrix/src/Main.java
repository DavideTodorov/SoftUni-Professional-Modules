import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numInputs = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        int rolls = numInputs[0];
        int cols = numInputs[1];

        int[][] matrix = readIntMatrix(scanner, rolls);

        int[][] bestMatrix = new int[2][2];
        int bestSum = Integer.MIN_VALUE;

        for (int rol = 0; rol < rolls - 1; rol++) {
            for (int col = 0; col < cols - 1; col++) {
                int currElement = matrix[rol][col];
                int rightElement = matrix[rol][col + 1];
                int downElement = matrix[rol + 1][col];
                int diagonalElement = matrix[rol + 1][col + 1];
                int sum = currElement + rightElement + downElement + diagonalElement;

                if (sum > bestSum) {
                    bestSum = sum;
                    bestMatrix[0][0] = currElement;
                    bestMatrix[0][1] = rightElement;
                    bestMatrix[1][0] = downElement;
                    bestMatrix[1][1] = diagonalElement;
                }
            }
        }

        for (int[] ints : bestMatrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println(bestSum);
    }

    private static int[][] readIntMatrix(Scanner scanner, int rolls) {
        int[][] matrix = new int[rolls][];
        for (int rol = 0; rol < rolls; rol++) {
            matrix[rol] = Arrays.stream(scanner.nextLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
