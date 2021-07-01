import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.stream.Stream;

public class GetEmployeeWithProject {

    public static void main(String[] args) {
        /*      8. Get Employee with Project       */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        int idInput = Integer.parseInt(scanner.nextLine());

        Employee employee = em.createQuery("select e from Employee as e " +
                "where e.id = :input_id", Employee.class)
                .setParameter("input_id", idInput)
                .getSingleResult();

        Stream<String> sortedProjectsNames = employee.getProjects().stream().map(Project::getName).sorted(String::compareTo);

        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        sortedProjectsNames.forEach(System.out::println);
    }
}
