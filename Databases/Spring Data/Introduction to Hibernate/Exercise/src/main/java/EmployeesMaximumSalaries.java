import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMaximumSalaries {

    public static void main(String[] args) {
        /*      12.	Employees Maximum Salaries      */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Object[]> resultList = em.createQuery("select e.department.name, max(e.salary) from Employee e group by e.department.name " +
                "having max(e.salary) not between 30000 and 70000")
                .getResultList();

        for (Object[] o : resultList) {
            System.out.printf("%s %.2f%n", o[0], o[1]);
        }


    }
}
