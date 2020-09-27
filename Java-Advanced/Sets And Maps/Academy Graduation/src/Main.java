import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputSize = Integer.parseInt(scanner.nextLine());

        Map<String, List<Double>> studentsWithGrades = new TreeMap<>();

        for (int i = 0; i < inputSize; i++) {
            String name = scanner.nextLine();
            double[] grades = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToDouble(Double::parseDouble).toArray();

            studentsWithGrades.putIfAbsent(name, new ArrayList<>());

            for (int j = 0; j < grades.length; j++) {
                studentsWithGrades.get(name).add(grades[j]);
            }
        }

        studentsWithGrades.entrySet()
                .forEach(student -> {
                    double average = 0;
                    for (int i = 0; i < student.getValue().size(); i++) {
                        double curr = student.getValue().get(i);
                        average += curr;
                    }
                    average = average / student.getValue().size();

                    DecimalFormat format = new DecimalFormat("0.#####################");
                    String formatted = format.format(average);
                    System.out.printf("%s is graduated with %s%n", student.getKey(), formatted);
                });
    }
}
