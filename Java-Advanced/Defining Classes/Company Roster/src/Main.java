import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<CompanyEmployees>> allEmployees = new TreeMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                String name = tokens[0];
                double salary = Double.parseDouble(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];

                CompanyEmployees companyEmployee = new CompanyEmployees(name, salary,
                        position, department);

                allEmployees.putIfAbsent(department, new ArrayList<>());

                allEmployees.get(department).add(companyEmployee);

            } else if (tokens.length == 5 && Character.isAlphabetic(tokens[4].charAt(0))) {
                String name = tokens[0];
                double salary = Double.parseDouble(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];
                String email = tokens[4];

                CompanyEmployees companyEmployee = new CompanyEmployees(name, salary,
                        position, department,
                        email);
                allEmployees.putIfAbsent(department, new ArrayList<>());

                allEmployees.get(department).add(companyEmployee);

            } else if (tokens.length == 5 && Character.isDigit(tokens[4].charAt(0))) {
                String name = tokens[0];
                double salary = Double.parseDouble(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];
                int age = Integer.parseInt(tokens[4]);

                CompanyEmployees companyEmployee = new CompanyEmployees(name, salary,
                        position, department, age);

                allEmployees.putIfAbsent(department, new ArrayList<>());

                allEmployees.get(department).add(companyEmployee);

            } else if (tokens.length == 6) {
                String name = tokens[0];
                double salary = Double.parseDouble(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];
                String email = tokens[4];
                int age = Integer.parseInt(tokens[5]);

                CompanyEmployees companyEmployee = new CompanyEmployees(name, salary, position,
                        department, email, age);

                allEmployees.putIfAbsent(department, new ArrayList<>());

                allEmployees.get(department).add(companyEmployee);
            }
        }


        allEmployees.entrySet()
                .stream()
                .sorted((x1, x2) -> {

                    List<CompanyEmployees> x1Value = x1.getValue();
                    List<CompanyEmployees> x2Value = x2.getValue();

                    double x1Average = 0;
                    for (CompanyEmployees employee : x1Value) {
                        x1Average += employee.getSalary();
                    }
                    x1Average /= x1Value.size();

                    double x2Average = 0;
                    for (CompanyEmployees employee : x2Value) {
                        x2Average += employee.getSalary();
                    }
                    x2Average /= x2Value.size();

                    return Double.compare(x2Average, x1Average);

                })
                .limit(1)
                .forEach(department -> {
                    System.out.printf("Highest Average Salary: %s%n", department.getKey());
                    List<CompanyEmployees> employeesInDepartment = department.getValue();

                    employeesInDepartment.stream()
                            .sorted((e1, e2) -> {
                                return Double.compare(e2.getSalary(), e1.getSalary());
                            })
                            .forEach(e -> System.out.println(e.toString()));
                });
    }
}
