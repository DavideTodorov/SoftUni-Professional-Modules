import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmployeesWithSalaryOver50000 {

    public static void main(String[] args) {
        /*      4. Employees with Salary Over 50 000       */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT e.firstName FROM Employee AS e where e.salary > 50000");

        for (Object o : query.getResultList()) {

            System.out.println(o);
        }

        em.getTransaction().commit();
    }
}
