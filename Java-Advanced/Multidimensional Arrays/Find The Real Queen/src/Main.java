import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeOfBoard = 8;
        char[][] matrix = readCharMatrix(scanner, 8, 8);

        for (int rol = 0; rol < 8; rol++) {
            for (int col = 0; col < 8; col++) {
                char curr = matrix[rol][col];

                if (curr == 'q') {
                    if (isValid(matrix, rol, col)) {
                        System.out.println(rol + " " + col);
                        break;
                    }
                }
            }
        }
    }

    private static boolean isValid(char[][] matrix, int rol, int col) {
        int rolOriginal = rol;
        int colOriginal = col;
        //UP
        while (rol >= 1) {
            rol--;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;

        //DOWN
        while (rol < matrix.length - 1) {
            rol++;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;

        //LEFT
        while (col >= 1) {
            col--;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;


        //RIGHT
        while (col < matrix[rol].length - 1) {
            col++;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;


        //DIAGONALS:

        //RIGHT-DOWN
        while (rol < matrix.length - 1 && col < matrix[rol].length - 1) {
            rol++;
            col++;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;


        //RIGHT-UP
        while (rol >= 1 && col < matrix[rol].length - 1) {
            rol--;
            col++;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;


        //LEFT-UP
        while (rol >= 1 && col >= 1) {
            rol--;
            col--;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }
        rol = rolOriginal;
        col = colOriginal;


        //LEFT-DOWN
        while (rol < matrix.length - 1 && col >= 1) {
            rol++;
            col--;
            char curr = matrix[rol][col];
            if (curr == 'q') {
                return false;
            }
        }

        return true;
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
}

