import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int rolls = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        String[][] matrix = new String[rolls][];

        for (int rol = 0; rol < rolls; rol++) {
            matrix[rol] = scanner.nextLine().split("\\s+");
        }

        String[] input = scanner.nextLine().split("\\s+");
        while (!"END".equals(input[0])) {

            if (input.length == 5 && input[0].equals("swap")) {
                int rolOne = Integer.parseInt(input[1]);
                int colOne = Integer.parseInt(input[2]);
                int rolTwo = Integer.parseInt(input[3]);
                int colTwo = Integer.parseInt(input[4]);

                try {
                    String temp = matrix[rolOne][colOne];
                    matrix[rolOne][colOne] = matrix[rolTwo][colTwo];
                    matrix[rolTwo][colTwo] = temp;

                    for (String[] strings : matrix) {
                        for (String string : strings) {
                            System.out.print(string + " ");
                        }
                        System.out.println();
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid input!");
                }

            } else {
                System.out.println("Invalid input!");
            }
            input = scanner.nextLine().split("\\s+");
        }
    }
}