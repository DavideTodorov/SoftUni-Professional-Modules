public class BankAccount {

    private static int currAccountNumber = 1;
    private static double interestRate = 0.02;

    private int id;
    private double balance;

    public BankAccount() {
        this.id = currAccountNumber++;
    }

    public void Deposit(double amount) {
        this.balance += amount;
    }

    public static void SetInterest(double newInterestRate) {
        BankAccount.interestRate = newInterestRate;
    }

    public double getInterest(int years) {
        return this.balance * years * BankAccount.interestRate;
    }

    public int getId() {
        return id;
    }
}
