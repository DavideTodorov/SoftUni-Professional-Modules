import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixRows = scanner.nextInt();
        int matrixCols = scanner.nextInt();
        scanner.nextLine();

        int[][] matrix = new int[matrixRows][matrixCols];

        for (int row = 0; row < matrixRows; row++) {
            for (int col = 0; col < matrixCols; col++) {
                matrix[row][col] = 0;
            }
        }

        List<int[]> flowersCoordinates = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"Bloom Bloom Plow".equals(input)) {
            int[] coordinates = Arrays.stream(input.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int row = coordinates[0];
            int col = coordinates[1];

            if (isInBounds(row, col, matrix)) {
                flowersCoordinates.add(coordinates);
            } else {
                System.out.println("Invalid coordinates.");
            }

            input = scanner.nextLine();
        }

        //BLOOMING
        for (int[] flowersCoordinate : flowersCoordinates) {
            int row = flowersCoordinate[0];
            int col = flowersCoordinate[1];

            int startingRow = row;
            int startingCol = col;

            matrix[row][col] = 1;

            //UP
            row--;
            while (isInBounds(row, col, matrix)) {
                int currFlower = matrix[row][col];
                currFlower++;
                matrix[row][col] = currFlower;
                row--;
            }

            row = startingRow;
            col = startingCol;

            //DOWN
            row++;
            while (isInBounds(row, col, matrix)) {
                int currFlower = matrix[row][col];
                currFlower++;
                matrix[row][col] = currFlower;
                row++;
            }

            row = startingRow;
            col = startingCol;

            //LEFT
            col--;
            while (isInBounds(row, col, matrix)) {
                int currFlower = matrix[row][col];
                currFlower++;
                matrix[row][col] = currFlower;
                col--;
            }

            row = startingRow;
            col = startingCol;

            //RIGHT
            col++;
            while (isInBounds(row, col, matrix)) {
                int currFlower = matrix[row][col];
                currFlower++;
                matrix[row][col] = currFlower;
                col++;
            }
        }

        for (int[] innerArr : matrix) {
            for (int curr : innerArr) {
                System.out.print(curr + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int row, int col, int[][] matrix) {
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[row].length;
    }
}