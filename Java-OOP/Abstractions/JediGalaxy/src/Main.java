import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static long sum = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensionsInput = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = dimensionsInput[0];
        int y = dimensionsInput[1];

        int[][] matrix = createArray(x, y);


        String command = scanner.nextLine();
        while (!command.equals("Let the Force be with you")) {

            int[] ivoCoordinates = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int rowIvo = ivoCoordinates[0];
            int colIvo = ivoCoordinates[1];

            int rowEvil = evilCoordinates[0];
            int colEvil = evilCoordinates[1];

            iterEvilCoordinates(matrix, rowEvil, colEvil);

            iterIvoCoordinates(matrix, rowIvo, colIvo);

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    private static void iterEvilCoordinates(int[][] matrix, int rowEvil, int colEvil) {
        while (rowEvil >= 0 && colEvil >= 0) {
            if (indexInBounds(matrix, rowEvil, colEvil)) {
                matrix[rowEvil][colEvil] = 0;
            }
            rowEvil--;
            colEvil--;
        }
    }

    private static boolean indexInBounds(int[][] matrix, int rowEvil, int colEvil) {
        return rowEvil >= 0 && rowEvil < matrix.length && colEvil >= 0 && colEvil < matrix[0].length;
    }

    private static void iterIvoCoordinates(int[][] matrix, int rowIvo, int colIvo) {
        while (rowIvo >= 0 && colIvo < matrix[1].length) {
            if (indexInBounds(matrix, rowIvo, colIvo)) {
                sum += matrix[rowIvo][colIvo];
            }

            colIvo++;
            rowIvo--;
        }
    }

    private static int[][] createArray(int x, int y) {
        int[][] matrix = new int[x][y];
        int value = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }
}
