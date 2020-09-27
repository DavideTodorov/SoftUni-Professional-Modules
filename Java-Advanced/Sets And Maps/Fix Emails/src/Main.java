import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> emails = new LinkedHashMap<>();

        String inputName = scanner.nextLine();
        while (!"stop".equals(inputName)) {
            String email = scanner.nextLine();

            String domain = email.substring(email.indexOf(".") + 1);

            if (!domain.equals("us") && !domain.equals("uk") && !domain.equals("com")) {
                emails.put(inputName, email);
            }

            inputName = scanner.nextLine();
        }

        emails.entrySet()
                .forEach(x -> {
                    System.out.printf("%s -> %s%n", x.getKey(), x.getValue());
                });
    }
}
