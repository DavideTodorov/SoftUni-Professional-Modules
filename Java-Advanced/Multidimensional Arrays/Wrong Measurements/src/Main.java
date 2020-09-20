import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rolls = Integer.parseInt(scanner.nextLine());
        int[][] matrix = readIntMatrix(scanner, rolls);

        String[] inputs = scanner.nextLine().split("\\s+");
        int wrongNumberRollIndex = Integer.parseInt(inputs[0]);
        int wrongNumberColIndex = Integer.parseInt(inputs[1]);

        int wrongNumber = matrix[wrongNumberRollIndex][wrongNumberColIndex];

        ArrayList<int[]> correctIndexes = new ArrayList<>();
        ArrayList<Integer> correctValues = new ArrayList<>();

        for (int rol = 0; rol < matrix.length; rol++) {
            for (int col = 0; col < matrix[rol].length; col++) {
                if (matrix[rol][col] == wrongNumber) {
                    replaceValue(matrix, rol, col, wrongNumber,
                            correctIndexes, correctValues);
                }
            }
        }

        for (int i = 0; i < correctIndexes.size(); i++) {
            int rol = correctIndexes.get(i)[0];
            int col = correctIndexes.get(i)[1];
            int correctValue = correctValues.get(i);

            matrix[rol][col] = correctValue;
        }

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static void replaceValue(int[][] matrix, int rol, int col, int wrongNumber,
                                     ArrayList<int[]> correctIndexes, ArrayList<Integer> correctValues) {
        int sumToReplace = 0;

        if (isValidIndex(matrix, rol + 1, col) && (matrix[rol + 1][col]) != wrongNumber) {
            sumToReplace += matrix[rol + 1][col];
        }

        if (isValidIndex(matrix, rol, col + 1) && (matrix[rol][col + 1]) != wrongNumber) {
            sumToReplace += matrix[rol][col + 1];
        }

        if (isValidIndex(matrix, rol, col - 1) && (matrix[rol][col - 1]) != wrongNumber) {
            sumToReplace += matrix[rol][col - 1];
        }

        if (isValidIndex(matrix, rol - 1, col) && (matrix[rol - 1][col]) != wrongNumber) {
            sumToReplace += matrix[rol - 1][col];
        }


        correctIndexes.add(new int[]{rol, col});
        correctValues.add(sumToReplace);
    }


    private static boolean isValidIndex(int[][] matrix, int rol, int col) {

        return (rol >= 0 && rol < matrix.length) && (col >= 0 && col < matrix[rol].length);
    }


    private static int[][] readIntMatrix(Scanner scanner, int rolls) {
        int[][] matrix = new int[rolls][];
        for (int rol = 0; rol < rolls; rol++) {
            matrix[rol] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
}
