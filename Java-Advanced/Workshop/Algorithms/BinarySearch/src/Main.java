import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sortedArr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();


        int element = Integer.parseInt(scanner.nextLine());

        int indexOfElement = findElement(sortedArr, element, 0, sortedArr.length);

        System.out.println(indexOfElement);
    }

    private static int findElement(int[] sortedArr, int element, int start, int end) {
        int middle = (start + end) / 2;

        if (end < start) {
            return -1;
        }

        if (element == sortedArr[middle]) {
            return middle;
        } else if (element < sortedArr[middle]) {
            return findElement(
                    sortedArr, element, start, middle - 1);
        } else {
            return findElement(
                    sortedArr, element, middle + 1, end);
        }
    }
}
