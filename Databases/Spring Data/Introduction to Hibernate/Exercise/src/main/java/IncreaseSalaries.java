import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class IncreaseSalaries {

    public static void main(String[] args) {
        /*      10.	Increase Salaries       */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        int i = em.createQuery("update Employee e set e.salary = e.salary * 1.12where e.department.id in :incList")
                .setParameter("incList", Set.of(1, 2, 4, 11))
                .executeUpdate();
        System.out.println(i);

        List<Employee> resultList = em.createQuery("select e from Employee e where e.department.id in :nameList", Employee.class)
                .setParameter("nameList",  Set.of(1, 2, 4, 11))
                .getResultList();

        em.getTransaction().commit();

        for (Employee employee : resultList) {
            System.out.printf("%s %s ($%.2f)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary());
        }
    }
}
