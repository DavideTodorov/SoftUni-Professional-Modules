import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> usersSpentTime = new TreeMap<>();
        Map<String, TreeSet<String>> usersIp = new TreeMap<>();

        int inputLinesSize = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < inputLinesSize; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String ip = tokens[0];
            String name = tokens[1];
            int timeSpent = Integer.parseInt(tokens[2]);

            //Adding to Time map
            usersSpentTime.putIfAbsent(name, 0);
            usersSpentTime.put(name, usersSpentTime.get(name) + timeSpent);

            //Adding to IP map
            usersIp.putIfAbsent(name, new TreeSet<>());
            usersIp.get(name).add(ip);
        }

        for (Map.Entry<String, Integer> entry : usersSpentTime.entrySet()) {
            String userName = entry.getKey();
            int userTime = entry.getValue();
            TreeSet<String> userIPes = usersIp.get(userName);

            System.out.printf("%s: %d [%s]%n", userName, userTime, String.join(", ", userIPes));
        }
    }
}