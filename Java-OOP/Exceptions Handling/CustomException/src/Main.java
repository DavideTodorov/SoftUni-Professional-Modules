import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String[] tokens = scanner.nextLine().split("\\s+");
            Student student = new Student(tokens[0], tokens[1]);
            System.out.println(student.toString());
        } catch (InvalidPersonNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
