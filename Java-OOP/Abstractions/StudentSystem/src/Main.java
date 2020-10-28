import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentSystem studentSystem = new StudentSystem();

        String input = scanner.nextLine();
        while (!"Exit".equals(input)) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            if (command.equals("Create")) {
                studentSystem.create(tokens);
            } else if (command.equals("Show")) {
                studentSystem.show(tokens);
            }

            input = scanner.nextLine();
        }
    }
}