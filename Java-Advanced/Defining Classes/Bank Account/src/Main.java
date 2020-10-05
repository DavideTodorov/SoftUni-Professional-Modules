import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, BankAccount> bankAccounts = new HashMap<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");

            String command = tokens[0];

            switch (command) {
                case "Create":
                    BankAccount bankAccount = new BankAccount();
                    System.out.printf("Account ID%d created%n", bankAccount.getId());
                    bankAccounts.put(bankAccount.getId(), bankAccount);
                    break;

                case "Deposit":
                    int accountId = Integer.parseInt(tokens[1]);
                    double sum = Double.parseDouble(tokens[2]);

                    if (bankAccounts.containsKey(accountId)) {
                       bankAccounts.get(accountId).Deposit(sum);
                        DecimalFormat format = new DecimalFormat("##,####");
                        String formatted = format.format(sum);
                        System.out.printf("Deposited %s to ID%d%n", formatted, accountId);
                    } else {
                        System.out.println("Account does not exist");
                    }

                    break;

                case "SetInterest":
                    double interest = Double.parseDouble(tokens[1]);
                    BankAccount.SetInterest(interest);
                    break;

                case "GetInterest":
                    accountId = Integer.parseInt(tokens[1]);

                    if (bankAccounts.containsKey(accountId)) {
                        int years = Integer.parseInt(tokens[2]);
                        double totalInterest = bankAccounts.get(accountId).getInterest(years);
                        System.out.printf("%.2f%n", totalInterest);
                    } else {
                        System.out.println("Account does not exist");
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }
}