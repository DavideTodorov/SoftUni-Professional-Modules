import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int squareMatrixSize = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[squareMatrixSize][];
        for (int i = 0; i < squareMatrixSize; i++) {
            matrix[i] = scanner.nextLine().split("");
        }

        int rowOfBee = 0;
        int colOfBee = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")) {
                    rowOfBee = i;
                    colOfBee = j;
                }
            }
        }

        int flowersCount = 0;
        String input = scanner.nextLine();
        while (!"End".equals(input)) {

            switch (input) {
                case "up":
                    int oldRow = rowOfBee;
                    rowOfBee--;

                    if (rowOfBee >= 0) {
                        if (matrix[rowOfBee][colOfBee].equals("f")) {
                            flowersCount++;
                            matrix[oldRow][colOfBee] = ".";
                            matrix[rowOfBee][colOfBee] = "B";

                        } else if (matrix[rowOfBee][colOfBee].equals("O")) {
                            matrix[rowOfBee][colOfBee] = "B";
                            matrix[oldRow][colOfBee] = ".";

                            oldRow = rowOfBee;
                            rowOfBee--;

                            if (rowOfBee >= 0) {
                                if (matrix[rowOfBee][colOfBee].equals("f")) {
                                    flowersCount++;
                                }
                                matrix[rowOfBee][colOfBee] = "B";
                                matrix[oldRow][colOfBee] = ".";

                            } else {
                                System.out.println("The bee got lost!");
                                matrix[oldRow][colOfBee] = ".";
                                print(matrix, flowersCount);
                                return;
                            }
                        } else {
                            matrix[rowOfBee][colOfBee] = "B";
                            matrix[oldRow][colOfBee] = ".";
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[oldRow][colOfBee] = ".";
                        print(matrix, flowersCount);
                        return;
                    }
                    break;

                case "down":
                    oldRow = rowOfBee;
                    rowOfBee++;
                    if (rowOfBee < matrix.length) {
                        if (matrix[rowOfBee][colOfBee].equals("f")) {
                            flowersCount++;
                            matrix[oldRow][colOfBee] = ".";
                            matrix[rowOfBee][colOfBee] = "B";

                        } else if (matrix[rowOfBee][colOfBee].equals("O")) {
                            matrix[rowOfBee][colOfBee] = "B";
                            matrix[oldRow][colOfBee] = ".";

                            oldRow = rowOfBee;
                            rowOfBee++;

                            if (rowOfBee < matrix.length) {
                                if (matrix[rowOfBee][colOfBee].equals("f")) {
                                    flowersCount++;
                                }
                                matrix[rowOfBee][colOfBee] = "B";
                                matrix[oldRow][colOfBee] = ".";
                            } else {
                                System.out.println("The bee got lost!");
                                matrix[oldRow][colOfBee] = ".";
                                print(matrix, flowersCount);
                                return;
                            }
                        } else {
                            matrix[rowOfBee][colOfBee] = "B";
                            matrix[oldRow][colOfBee] = ".";
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[oldRow][colOfBee] = ".";
                        print(matrix, flowersCount);
                        return;
                    }

                    break;

                case "left":
                    int oldCol = colOfBee;
                    colOfBee--;
                    if (colOfBee >= 0) {
                        if (matrix[rowOfBee][colOfBee].equals("f")) {
                            matrix[rowOfBee][oldCol] = ".";
                            matrix[rowOfBee][colOfBee] = "B";
                            flowersCount++;
                        } else if (matrix[rowOfBee][colOfBee].equals("O")) {
                            matrix[rowOfBee][oldCol] = ".";
                            matrix[rowOfBee][colOfBee] = "B";

                            oldCol = colOfBee;
                            colOfBee--;

                            if (colOfBee >= 0) {
                                if (matrix[rowOfBee][colOfBee].equals("f")) {
                                    flowersCount++;
                                }
                                matrix[rowOfBee][oldCol] = ".";
                                matrix[rowOfBee][colOfBee] = "B";
                            } else {
                                System.out.println("The bee got lost!");
                                matrix[rowOfBee][oldCol] = ".";
                                print(matrix, flowersCount);
                                return;
                            }
                        } else {
                            matrix[rowOfBee][oldCol] = ".";
                            matrix[rowOfBee][colOfBee] = "B";
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[rowOfBee][oldCol] = ".";
                        print(matrix, flowersCount);
                        return;
                    }
                    break;

                case "right":
                    oldCol = colOfBee;
                    colOfBee++;
                    if (colOfBee < matrix.length) {
                        if (matrix[rowOfBee][colOfBee].equals("f")) {
                            matrix[rowOfBee][oldCol] = ".";
                            matrix[rowOfBee][colOfBee] = "B";
                            flowersCount++;
                        } else if (matrix[rowOfBee][colOfBee].equals("O")) {
                            matrix[rowOfBee][oldCol] = ".";
                            matrix[rowOfBee][colOfBee] = "B";

                            oldCol = colOfBee;
                            colOfBee++;

                            if (colOfBee < matrix.length) {
                                if (matrix[rowOfBee][colOfBee].equals("f")) {
                                    flowersCount++;
                                }
                                matrix[rowOfBee][oldCol] = ".";
                                matrix[rowOfBee][colOfBee] = "B";

                            } else {
                                System.out.println("The bee got lost!");
                                matrix[rowOfBee][oldCol] = ".";
                                print(matrix, flowersCount);
                                return;
                            }
                        } else {
                            matrix[rowOfBee][oldCol] = ".";
                            matrix[rowOfBee][colOfBee] = "B";
                        }
                    } else {
                        System.out.println("The bee got lost!");
                        matrix[rowOfBee][oldCol] = ".";
                        print(matrix, flowersCount);
                        return;
                    }
                    break;
            }
            input = scanner.nextLine();
        }

        print(matrix, flowersCount);
    }

    private static void print(String[][] matrix, int flowersCount) {
        if (flowersCount >= 5) {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", flowersCount);
        } else {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", 5 - flowersCount);
        }

        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }
}