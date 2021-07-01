import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {

    public static void main(String[] args) {
        /*      11.	Find Employees by First Name        */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String prefix = scanner.nextLine();

        List<Employee> employees = em.createQuery("select e from Employee e where e.firstName like concat(:start_inpt, '%')",
                Employee.class)
                .setParameter("start_inpt", prefix)
                .getResultList();

        for (Employee e : employees) {
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getJobTitle(),
                    e.getSalary());
        }
    }
}
