import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeOfBoard = 8;
        char[][] matrix = readCharMatrix(scanner, 8, 8);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                char curr = matrix[row][col];

                if (curr == 'q') {
                    if (isValid(matrix, row, col)) {
                        System.out.println(row + " " + col);
                        break;
                    }
                }
            }
        }
    }

    private static boolean isValid(char[][] matrix, int row, int col) {
        int rolOriginal = row;
        int colOriginal = col;
        //UP
        while (row >= 1) {
            row--;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;

        //DOWN
        while (row < matrix.length - 1) {
            row++;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;

        //LEFT
        while (col >= 1) {
            col--;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;


        //RIGHT
        while (col < matrix[row].length - 1) {
            col++;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;


        //DIAGONALS:

        //RIGHT-DOWN
        while (row < matrix.length - 1 && col < matrix[row].length - 1) {
            row++;
            col++;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;


        //RIGHT-UP
        while (row >= 1 && col < matrix[row].length - 1) {
            row--;
            col++;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;


        //LEFT-UP
        while (row >= 1 && col >= 1) {
            row--;
            col--;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }
        row = rolOriginal;
        col = colOriginal;


        //LEFT-DOWN
        while (row < matrix.length - 1 && col >= 1) {
            row++;
            col--;
            char curr = matrix[row][col];
            if (curr == 'q') {
                return false;
            }
        }

        return true;
    }

    private static char[][] readCharMatrix(Scanner scanner, int row, int cols) {
        char[][] matrix = new char[row][cols];
        for (int i = 0; i < row; i++) {
            String[] elements = scanner.nextLine().split("\\s+");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = elements[j].charAt(0);
            }
        }
        return matrix;
    }
}

