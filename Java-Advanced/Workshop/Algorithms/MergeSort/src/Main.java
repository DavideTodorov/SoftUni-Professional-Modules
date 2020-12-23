import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        mergeSort(arr, arr.length);

        System.out.println(Arrays.stream(arr)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    public static void mergeSort(int[] arr, int arrLength) {
        if (arrLength < 2) {
            return;
        }

        int mid = arrLength / 2;
        int[] left = new int[mid];
        int[] right = new int[arrLength - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        for (int i = mid; i < arrLength; i++) {
            right[i - mid] = arr[i];
        }

        mergeSort(left, mid);
        mergeSort(right, arrLength - mid);

        merge(arr, left, right, mid, arrLength - mid);
    }

    public static void merge(
            int[] arr, int[] leftArr, int[] rightArr, int left, int right) {

        int leftArrIndex = 0, rightArrIndex = 0, actualIndex = 0;

        while (leftArrIndex < left && rightArrIndex < right) {
            if (leftArr[leftArrIndex] <= rightArr[rightArrIndex]) {
                arr[actualIndex++] = leftArr[leftArrIndex++];
            }
            else {
                arr[actualIndex++] = rightArr[rightArrIndex++];
            }
        }

        while (leftArrIndex < left) {
            arr[actualIndex++] = leftArr[leftArrIndex++];
        }

        while (rightArrIndex < right) {
            arr[actualIndex++] = rightArr[rightArrIndex++];
        }
    }
}
