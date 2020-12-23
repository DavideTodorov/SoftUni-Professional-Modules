import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        int factorial = findFactorial(number);

        System.out.println(factorial);
    }

    private static int findFactorial(int number) {
        if (number == 1) {
            return number;
        }

        return number * findFactorial(number - 1);
    }
}
