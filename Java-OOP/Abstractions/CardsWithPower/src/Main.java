import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RankPowers cardRank = RankPowers.valueOf(scanner.nextLine().toUpperCase());
        SuitPowers cardSuit = SuitPowers.valueOf(scanner.nextLine().toUpperCase());

        int totalCardPower = cardRank.getRankPower() + cardSuit.getSuitPower();

        System.out.println(String.format("Card name: %s of %s; Card power: %d",
                cardRank, cardSuit, totalCardPower));
    }
}
