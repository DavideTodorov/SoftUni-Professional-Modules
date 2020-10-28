import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bottomLeftX = scanner.nextInt();
        int bottomLeftY = scanner.nextInt();
        int topRightX = scanner.nextInt();
        int topRightY = scanner.nextInt();
        scanner.nextLine();

        Rectangle rectangle = new Rectangle(bottomLeftX, bottomLeftY,
                topRightX, topRightY);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            int inputX = scanner.nextInt();
            int inputY = scanner.nextInt();
            scanner.nextLine();

            Point point = new Point(inputX, inputY);

            System.out.println(rectangle.contains(point));
        }
    }
}
