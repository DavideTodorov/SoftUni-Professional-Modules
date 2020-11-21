import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        try {
            String[] tokens = scanner.nextLine().split("\\s+");
            Person person =
                    new Person(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            System.out.println(person.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}