import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[size][];

        for (int row = 0; row < size; row++) {
            matrix[row] = scanner.nextLine().split("");
        }

        int foodQuantity = 0;
        while (true) {
            String input = scanner.nextLine();

            boolean done = false;

            switch (input) {
                case "up":
                    for (int row = 0; row < matrix.length; row++) {
                        for (int col = 0; col < matrix[row].length; col++) {
                            String curr = matrix[row][col];

                            if (curr.equals("S")) {
                                int rollIndex = row - 1;
                                if (rollIndex >= 0) {
                                    if (matrix[row - 1][col].equals("B")) {
                                        int[] coordinates = findNextB(matrix, row - 1, col);
                                        matrix[row - 1][col] = ".";
                                        matrix[row][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[row - 1][col].equals("*")) {
                                        foodQuantity++;
                                        matrix[row][col] = ".";
                                        matrix[row - 1][col] = "S";
                                        if (foodQuantity == 10) {
                                            System.out.println("You won! You fed the snake.");
                                            System.out.println("Food eaten: 10");
                                            for (String[] strings : matrix) {
                                                for (String string : strings) {
                                                    System.out.print(string);
                                                }
                                                System.out.println();
                                            }
                                            return;
                                        }
                                        done = true;
                                        break;
                                    } else {
                                        matrix[row][col] = ".";
                                        matrix[row - 1][col] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[row][col] = ".";
                                    for (String[] strings : matrix) {
                                        for (String string : strings) {
                                            System.out.print(string);
                                        }
                                        System.out.println();
                                    }
                                    return;
                                }
                            }
                        }
                        if (done) {
                            break;
                        }
                    }
                    break;

                case "down":
                    for (int row = 0; row < matrix.length; row++) {
                        for (int col = 0; col < matrix[row].length; col++) {
                            String curr = matrix[row][col];

                            if (curr.equals("S")) {
                                int rollIndex = row + 1;
                                if (rollIndex < matrix.length) {
                                    if (matrix[row + 1][col].equals("B")) {
                                        int[] coordinates = findNextB(matrix, row + 1, col);
                                        matrix[row + 1][col] = ".";
                                        matrix[row][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[row + 1][col].equals("*")) {
                                        foodQuantity++;
                                        matrix[row][col] = ".";
                                        matrix[row + 1][col] = "S";
                                        if (foodQuantity == 10) {
                                            System.out.println("You won! You fed the snake.");
                                            System.out.println("Food eaten: 10");
                                            for (String[] strings : matrix) {
                                                for (String string : strings) {
                                                    System.out.print(string);
                                                }
                                                System.out.println();
                                            }
                                            return;
                                        }
                                        done = true;
                                        break;
                                    } else {
                                        matrix[row][col] = ".";
                                        matrix[row + 1][col] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[row][col] = ".";
                                    for (String[] strings : matrix) {
                                        for (String string : strings) {
                                            System.out.print(string);
                                        }
                                        System.out.println();
                                    }
                                    return;
                                }
                            }
                        }
                        if (done){
                            break;
                        }
                    }
                    break;

                case "left":
                    for (int row = 0; row < matrix.length; row++) {
                        for (int col = 0; col < matrix[row].length; col++) {
                            String curr = matrix[row][col];

                            if (curr.equals("S")) {
                                int colIndex = col - 1;
                                if (colIndex >= 0) {
                                    if (matrix[row][col - 1].equals("B")) {
                                        int[] coordinates = findNextB(matrix, row, col - 1);
                                        matrix[row][col - 1] = ".";
                                        matrix[row][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[row][col - 1].equals("*")) {
                                        foodQuantity++;
                                        matrix[row][col] = ".";
                                        matrix[row][col - 1] = "S";
                                        if (foodQuantity == 10) {
                                            System.out.println("You won! You fed the snake.");
                                            System.out.println("Food eaten: 10");
                                            for (String[] strings : matrix) {
                                                for (String string : strings) {
                                                    System.out.print(string);
                                                }
                                                System.out.println();
                                            }
                                            return;
                                        }
                                        done = true;
                                        break;
                                    } else {
                                        matrix[row][col] = ".";
                                        matrix[row][col - 1] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[row][col] = ".";
                                    for (String[] strings : matrix) {
                                        for (String string : strings) {
                                            System.out.print(string);
                                        }
                                        System.out.println();
                                    }
                                    return;
                                }
                            }
                        }
                        if (done){
                            break;
                        }
                    }
                    break;

                case "right":
                    for (int row = 0; row < matrix.length; row++) {
                        for (int col = 0; col < matrix[row].length; col++) {
                            String curr = matrix[row][col];

                            if (curr.equals("S")) {
                                int colIndex = col + 1;
                                if (colIndex < matrix[row].length) {
                                    if (matrix[row][col + 1].equals("B")) {
                                        int[] coordinates = findNextB(matrix, row, col + 1);
                                        matrix[row][col + 1] = ".";
                                        matrix[row][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[row][col + 1].equals("*")) {
                                        foodQuantity++;
                                        matrix[row][col] = ".";
                                        matrix[row][col + 1] = "S";
                                        if (foodQuantity == 10) {
                                            System.out.println("You won! You fed the snake.");
                                            System.out.println("Food eaten: 10");
                                            for (String[] strings : matrix) {
                                                for (String string : strings) {
                                                    System.out.print(string);
                                                }
                                                System.out.println();
                                            }
                                            return;
                                        }
                                        done = true;
                                        break;
                                    } else {
                                        matrix[row][col] = ".";
                                        matrix[row][col + 1] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[row][col] = ".";
                                    for (String[] strings : matrix) {
                                        for (String string : strings) {
                                            System.out.print(string);
                                        }
                                        System.out.println();
                                    }
                                    return;
                                }
                            }
                        }
                        if (done){
                            break;
                        }
                    }
                    break;
            }
        }
    }

    private static int[] findNextB(String[][] matrix, int row, int col) {
        int rollCoordinates = 0;
        int colCoordinates = 0;

        for (int rol = 0; rol < matrix.length; rol++) {
            for (int cols = 0; cols < matrix.length; cols++) {
                if (matrix[rol][cols].equals("B") && rol != row && cols != col) {
                    rollCoordinates = rol;
                    colCoordinates = cols;
                    return new int[]{rollCoordinates, colCoordinates};
                }
            }
        }

        return new int[]{rollCoordinates, colCoordinates};
    }
}