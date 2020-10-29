import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArr = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(inputArr[0]);
        int days = Integer.parseInt(inputArr[1]);
        Season season = Season.valueOf(inputArr[2].toUpperCase());
        DiscountType discountType = DiscountType.valueOf(inputArr[3].toUpperCase());

        double sumToPay = PriceCalculator.calculate(pricePerDay, days,
                season, discountType);

        System.out.println(String.format("%.2f", sumToPay));
    }
}
