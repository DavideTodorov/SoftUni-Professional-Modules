import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] robotsInput = scanner.nextLine().split(";");

        Map<String, Integer> robots = new LinkedHashMap<>();
        for (String s : robotsInput) {
            String[] currRobot = s.split("-");
            String name = currRobot[0];
            int time = Integer.parseInt(currRobot[1]);
            robots.put(name, time);
        }

        String[] timeInput = scanner.nextLine().split(":");
        int startingHours = Integer.parseInt(timeInput[0]);
        int startingMinutes = Integer.parseInt(timeInput[1]);
        int startingSeconds = Integer.parseInt(timeInput[2]);

        long totalSeconds = (startingHours * 3600) + (startingMinutes * 60) + startingSeconds;

        ArrayDeque<String> products = new ArrayDeque<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {
            products.offer(input);
            input = scanner.nextLine();
        }

        int[] workingTime = new int[robots.size()];
        while (!products.isEmpty()) {
            String currProduct = products.poll();
            totalSeconds++;

            boolean isAssigned = false;

            for (int i = 0; i < workingTime.length; i++) {
                if (workingTime[i] != 0) {
                    workingTime[i] -= 1;
                }
            }

            for (int i = 0; i < robots.size(); i++) {
                if (workingTime[i] == 0) {
                    int count = 0;
                    for (Map.Entry<String, Integer> entry : robots.entrySet()) {
                        if (count == i) {
                            String robotName = entry.getKey();
                            int time = entry.getValue();
                            workingTime[i] = time;
                            long hours = totalSeconds / 3600 % 24;
                            long minutes = totalSeconds % 3600 / 60;
                            long seconds = totalSeconds % 60;
                            System.out.printf("%s - %s [%02d:%02d:%02d]%n", robotName, currProduct,
                                    hours, minutes, seconds);
                            isAssigned = true;
                            break;
                        }
                        count++;
                    }
                    break;
                }
            }
            if (!isAssigned) {
                products.offer(currProduct);
            }
        }
    }
}