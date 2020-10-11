import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> text = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            Double input = Double.parseDouble(scanner.nextLine());
            text.add(input);
        }

        GenericCount<Double> genericCount = new GenericCount<>(text);
        Double element = Double.parseDouble(scanner.nextLine());
        System.out.println(genericCount.compare(element));
    }
}