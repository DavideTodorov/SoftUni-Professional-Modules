import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[size][];

        for (int rol = 0; rol < size; rol++) {
            matrix[rol] = scanner.nextLine().split("");
        }

        int foodQuantity = 0;
        while (true) {
            String input = scanner.nextLine();

            boolean done = false;

            switch (input) {
                case "up":
                    for (int rol = 0; rol < matrix.length; rol++) {
                        for (int col = 0; col < matrix[rol].length; col++) {
                            String curr = matrix[rol][col];

                            if (curr.equals("S")) {
                                int rollIndex = rol - 1;
                                if (rollIndex >= 0) {
                                    if (matrix[rol - 1][col].equals("B")) {
                                        int[] coordinates = findNextB(matrix, rol - 1, col);
                                        matrix[rol - 1][col] = ".";
                                        matrix[rol][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[rol - 1][col].equals("*")) {
                                        foodQuantity++;
                                        matrix[rol][col] = ".";
                                        matrix[rol - 1][col] = "S";
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
                                        matrix[rol][col] = ".";
                                        matrix[rol - 1][col] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[rol][col] = ".";
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
                    for (int rol = 0; rol < matrix.length; rol++) {
                        for (int col = 0; col < matrix[rol].length; col++) {
                            String curr = matrix[rol][col];

                            if (curr.equals("S")) {
                                int rollIndex = rol + 1;
                                if (rollIndex < matrix.length) {
                                    if (matrix[rol + 1][col].equals("B")) {
                                        int[] coordinates = findNextB(matrix, rol + 1, col);
                                        matrix[rol + 1][col] = ".";
                                        matrix[rol][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[rol + 1][col].equals("*")) {
                                        foodQuantity++;
                                        matrix[rol][col] = ".";
                                        matrix[rol + 1][col] = "S";
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
                                        matrix[rol][col] = ".";
                                        matrix[rol + 1][col] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[rol][col] = ".";
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
                    for (int rol = 0; rol < matrix.length; rol++) {
                        for (int col = 0; col < matrix[rol].length; col++) {
                            String curr = matrix[rol][col];

                            if (curr.equals("S")) {
                                int colIndex = col - 1;
                                if (colIndex >= 0) {
                                    if (matrix[rol][col - 1].equals("B")) {
                                        int[] coordinates = findNextB(matrix, rol, col - 1);
                                        matrix[rol][col - 1] = ".";
                                        matrix[rol][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[rol][col - 1].equals("*")) {
                                        foodQuantity++;
                                        matrix[rol][col] = ".";
                                        matrix[rol][col - 1] = "S";
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
                                        matrix[rol][col] = ".";
                                        matrix[rol][col - 1] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[rol][col] = ".";
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
                    for (int rol = 0; rol < matrix.length; rol++) {
                        for (int col = 0; col < matrix[rol].length; col++) {
                            String curr = matrix[rol][col];

                            if (curr.equals("S")) {
                                int colIndex = col + 1;
                                if (colIndex < matrix[rol].length) {
                                    if (matrix[rol][col + 1].equals("B")) {
                                        int[] coordinates = findNextB(matrix, rol, col + 1);
                                        matrix[rol][col + 1] = ".";
                                        matrix[rol][col] = ".";
                                        matrix[coordinates[0]][coordinates[1]] = "S";
                                        done = true;
                                        break;
                                    } else if (matrix[rol][col + 1].equals("*")) {
                                        foodQuantity++;
                                        matrix[rol][col] = ".";
                                        matrix[rol][col + 1] = "S";
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
                                        matrix[rol][col] = ".";
                                        matrix[rol][col + 1] = "S";
                                        done = true;
                                        break;
                                    }
                                } else {
                                    System.out.println("Game over!");
                                    System.out.println("Food eaten: " + foodQuantity);
                                    matrix[rol][col] = ".";
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

    private static int[] findNextB(String[][] matrix, int roll, int col) {
        int rollCoordinates = 0;
        int colCoordinates = 0;

        for (int rol = 0; rol < matrix.length; rol++) {
            for (int cols = 0; cols < matrix.length; cols++) {
                if (matrix[rol][cols].equals("B") && rol != roll && cols != col) {
                    rollCoordinates = rol;
                    colCoordinates = cols;
                    return new int[]{rollCoordinates, colCoordinates};
                }
            }
        }

        return new int[]{rollCoordinates, colCoordinates};
    }
}