import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputData = scanner.nextLine().split("[()]");
        int rotation = Integer.parseInt(inputData[1]) % 360;

        List<String> matrixInput = new ArrayList<>();

        String input = scanner.nextLine();
        int maxLength = Integer.MIN_VALUE;
        while (!input.equals("END")) {
            matrixInput.add(input);

            if (input.length() > maxLength) {
                maxLength = input.length();
            }

            input = scanner.nextLine();
        }

        String[][] matrix = new String[matrixInput.size()][];

        for (int i = 0; i < matrix.length; i++) {
            String currWord = matrixInput.get(i);
            String[] arr = new String[maxLength];
            for (int j = 0; j < maxLength; j++) {

                if (j >= currWord.length()) {
                    arr[j] = " ";
                } else {
                    arr[j] = String.valueOf(currWord.charAt(j));
                }
            }
            matrix[i] = arr;
        }


        switch (rotation) {
            case 90:
                for (int col = 0; col < maxLength; col++) {
                    for (int row = matrix.length - 1; row >= 0; row--) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;

            case 180:
                for (int row = matrix.length - 1; row >= 0; row--) {
                    for (int col = maxLength - 1; col >= 0; col--) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;

            case 270:
                for (int col = maxLength - 1; col >= 0; col--) {
                    for (int row = 0; row < matrix.length; row++) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;

            case 0:
                for (int row = 0; row < matrix.length; row++) {
                    for (int col = 0; col < maxLength; col++) {
                        System.out.print(matrix[row][col]);
                    }
                    System.out.println();
                }
                break;
        }
    }
}